package com.autoapitest.FunctionService.MealAndComsumer.meal;


import com.alibaba.fastjson.JSONObject;
import com.autoapitest.FunctionService.BaseTest;
import com.autoapitest.FunctionService.Function.addFunction.AddFuncitonTest;
import com.autoapitest.FunctionService.LoginTest;
import com.autoapitest.FunctionService.entity.MealEntity;
import com.autoapitest.FunctionService.params.Common.Environment;
import com.autoapitest.FunctionService.params.MealAndComsumer.AddMealReq;
import com.autoapitest.FunctionService.params.MealAndComsumer.DeleteMealReq;
import com.autoapitest.FunctionService.system.utils.JSON;
import com.autoapitest.FunctionService.system.utils.Tools;
import com.autoapitest.FunctionService.system.utils.XmlHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.dom4j.DocumentException;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 新增套餐测试方法
 * @author wing
 */

@Slf4j
public class AddMealTest extends BaseTest {

    Header[] loginheader ;
    public  List<Integer> list=new ArrayList<>();
    public static final String SERVER_GROUPS = "SERVER_GROUPS";
    public Integer Id=0;//塞ID


    /**
     * 获取之前执行的增加权限的id
     */
    @BeforeClass
    @SuppressWarnings("unchecked")
    public List<Integer> getIdList(ITestContext ctx){

        //获取增加权限类的数据

        list = (List<Integer>)ctx.getAttribute(AddFuncitonTest.SERVER_GROUPS);
        return this.list;
    }

    /**
     * 塞Id以便新增客户使用
     * @param ctx1
     */
    @AfterClass(alwaysRun = true)
    public void setMealId(ITestContext ctx1) {
        ctx1.setAttribute(SERVER_GROUPS, Id);
        log.info("此处为获得的套餐ID：："+this.Id);
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
     * 增加套餐
     * @param param
     * @param result
     * @return
     */
    @Parameters({"ENV"})
    @Test(dataProvider = "addmeal")
    public Integer addMeal(AddMealReq param, String result,String ENV) throws IOException {
        log.info("-----------------------------------------开始发起addMeal请求--------------------------------------------------------");

        //清理历史数据
        Integer deleteresult = deleteMealDataByName(param,loginheader,ENV);

        sleep(10);

        JSONObject response=null;

        //发送请求
        try{
            response = addmealservice.addMeal(param,result,loginheader, ENV);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //判断返回
        if("success".equals(JSON.getString(response,"massage"))){
            log.info("新增套餐成功");
        }

        //检查入库情况
        Integer checkadd= checkAddSuccess(param);
        Assert.assertEquals(checkadd.intValue(),1,"插入成功，且name一致");


        //获取id
        Long Id = getIdByname(param);

        log.info("-----------------------------------------结束addMeal请求--------------------------------------------------------");
        this.Id = new Tools().longToInteger(Id);

        return this.Id ;
    }

    @DataProvider(name = "addmeal")
    public Object[][] addMealData() throws IOException, DocumentException {

        XmlHandler xmlhandler= new XmlHandler();
        //获取xml文件的结果
        String result=xmlhandler.getresult(this.getClass().getSimpleName(),"addMeal","正常测试");
        //获取xml文件的参数
        JSONObject json =xmlhandler.getParam(this.getClass().getSimpleName(),"addMeal","正常测试");

//        //从xml取值处理list
//
//        String s= json.getString("functionIdList");
//        List<Integer> list = new StringToList().String2IntList(s);
//        //处理获取的参数
//        AddMealReq addmealparam=new AddMealReq(json.getString("duration"),list,null,json.getString("name"));

        //从接口取值处理list

        //处理获取的参数
         AddMealReq addmealparam=new AddMealReq(json.getString("duration"),this.list,null,json.getString("name"));

        //塞进dataprovider

        return  new Object[][]{{

                addmealparam,
                result,
        },

        };

    }

    /**
     * 根据套餐名称删除记录
     * @param param
     * @return
     */


    @Parameters({"ENV"})
    @Test
    public  Integer deleteMealDataByName(AddMealReq param,Header[] header,String ENV) throws IOException {

        //获取套餐id
        String name = param.getName();
        Long longid = mmpaper.getIdByname(name);

        Integer id = Integer.parseInt(String.valueOf(longid));

        //调用删除接口

        DeleteMealReq deleteparam = new DeleteMealReq(id);

        JSONObject deleteresponse = deletemealservice.deleteMeal(deleteparam,"操作成功",header, ENV);

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
    public Integer checkAddSuccess(AddMealReq request){
        Integer isdelete = 0;
        String responsename = request.getName();
        List<MealEntity> meal =mmpaper.getMealByName(responsename);

        String dataname=meal.get(0).getName();
        Assert.assertEquals(dataname,responsename);
        return 1;

    }

    /**
     * 根据name获取套餐id
     * @param request
     * @return
     */

    public Long getIdByname(AddMealReq request ){
        String name = request.getName();
        Long Id = mmpaper.getIdByname(name);

        return Id;
    }
}
