package com.autoapitest.FunctionService.MealAndComsumer.consumer;


import com.alibaba.fastjson.JSONObject;
import com.autoapitest.FunctionService.BaseTest;
import com.autoapitest.FunctionService.LoginTest;
import com.autoapitest.FunctionService.MealAndComsumer.meal.AddMealTest;
import com.autoapitest.FunctionService.entity.ComsumerEntity;
import com.autoapitest.FunctionService.params.Common.Environment;
import com.autoapitest.FunctionService.params.MealAndComsumer.AddConsumerReq;
import com.autoapitest.FunctionService.params.MealAndComsumer.DeleteConsumerReq;
import com.autoapitest.FunctionService.system.utils.JSON;
import com.autoapitest.FunctionService.system.utils.XmlHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.dom4j.DocumentException;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.List;

/**
 * 新增公司测试方法
 * @author wing
 */

@Slf4j
public class AddComsumerTest extends BaseTest {

    Header[] loginheader ;
    public static final String SERVER_GROUPS = "SERVER_GROUPS";
    public JSONObject json=new JSONObject();
    public Integer mealId=0;


    /**
     * 塞Id以便新增客户使用
     * @param ctx
     */
    @AfterClass(alwaysRun = true)
    public void setMealId(ITestContext ctx) {
        ctx.setAttribute(SERVER_GROUPS, json);
    }

    @BeforeClass
    public Integer getId(ITestContext ctx1){
        mealId= (Integer) ctx1.getAttribute(AddMealTest.SERVER_GROUPS);
        log.info("这是从上文获取的Id：：："+mealId);

        return mealId;
    }

    /**
     * 登录获取header
     */

    @Parameters({"ENV"})
    @BeforeMethod
    public Header[] getHeaders(String ENV){

        this.loginheader = new LoginTest().loginNormal("adminplatform","8888","123456",0,ENV);

        return loginheader;

    }

    /**
     * 增加公司
     * @param param
     * @param result
     * @return
     */
    @Parameters({"ENV"})
    @Test(dataProvider = "addcomsumer")
    public JSONObject addComsumer(AddConsumerReq param, String result,String ENV) throws IOException {
        log.info("-----------------------------------------开始发起addComsumer请求--------------------------------------------------------");

        //清理历史数据
        Integer deleteresult =deleteDataByName(param,loginheader,ENV);

        sleep(10);

        JSONObject response=null;

        //发送请求
        try{
            response = addcomsumerservice.addComsumer(param,result,loginheader,ENV);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //判断返回
        if("success".equals(JSON.getString(response,"massage"))){
            log.info("新增客户成功");
        }

        //检查入库情况
        Integer checkadd= checkAddSuccess(param);
        Assert.assertEquals(checkadd.intValue(),1,"插入成功，且name一致");

        //获取id
        Integer Id = (Integer) getIdByname(param);

        log.info("-----------------------------------------结束addComsumer请求--------------------------------------------------------");
        this.json.put("consumer",Id);
        this.json.put("phone",param.getAccount());

        return this.json ;
    }

    @DataProvider(name = "addcomsumer")
    public Object[][] addComsumer() throws IOException, DocumentException {

        XmlHandler xmlhandler= new XmlHandler();
        //获取xml文件的结果
        String result=xmlhandler.getresult(this.getClass().getSimpleName(),"addComsumer","正常测试");
        //获取xml文件的参数
        JSONObject json =xmlhandler.getParam(this.getClass().getSimpleName(),"addComsumer","正常测试");

        //处理获取的参数
        AddConsumerReq addcomsumerparam = new AddConsumerReq(json.getString("account"),null,json.getString("name"),json.getInteger("setMealId"));

        //塞进dataprovider

        return  new Object[][]{{

                addcomsumerparam,
                result,
        },

        };

    }

    /**
     * 根据客户名称删除记录
     * @param param
     * @return
     */
    @Parameters({"ENV"})
    @Test
    public  Integer deleteDataByName(AddConsumerReq param,Header[] header,String ENV) throws IOException {

        //获取id
        String name = param.getName();
        Integer Id = cmapper.getIdByname(name);

        DeleteConsumerReq deleteparam = new DeleteConsumerReq(Id);

        //调用删除公司接口
        JSONObject deleteresponse = deletecomsumerservice.deleteComsumer(deleteparam,"操作成功",header,ENV);

        //处理返回
        Integer flag=0;

        Integer status = JSON.getInt(deleteresponse,"status");

        if (status==200){
            flag=1;
            log.info("删除标志：：："+flag);
        }

        return flag;


    }


    /**
     * 根据name查看是否添加成功
     * @param request
     * @return
     */
    public Integer checkAddSuccess(AddConsumerReq request){
        Integer isdelete = 0;
        String requestename = request.getName();
        List<ComsumerEntity> comsumerByName =cmapper.getComsumerByName(requestename);

        String dataname=comsumerByName.get(0).getName();
        Assert.assertEquals(dataname,requestename);
        return 1;

    }

    /**
     * 根据name获取套餐id
     * @param request
     * @return
     */

    public Integer getIdByname(AddConsumerReq request ){
        String name = request.getName();
        Integer Id = cmapper.getIdByname(name);

        return Id;
    }
}
