package com.autoapitest.FunctionService.User;


import com.alibaba.fastjson.JSONObject;
import com.autoapitest.FunctionService.BaseTest;
import com.autoapitest.FunctionService.LoginTest;
import com.autoapitest.FunctionService.MealAndComsumer.consumer.AddComsumerTest;
import com.autoapitest.FunctionService.entity.RoleEntity;
import com.autoapitest.FunctionService.params.Common.Environment;
import com.autoapitest.FunctionService.params.User.AddRoleReq;
import com.autoapitest.FunctionService.params.User.DeleteRoleReq;
import com.autoapitest.FunctionService.system.utils.JSON;
import com.autoapitest.FunctionService.system.utils.StringToList;
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
 * 新增角色测试方法
 * @author wing
 */

@Slf4j
public class AddRoleTest extends BaseTest {

    Header[] loginheader ;
    public static final String SERVER_GROUPS = "SERVER_GROUPS";
    public JSONObject comsumerinfo=new JSONObject();//拿客户ID
    public  List<Integer> rolelist = new ArrayList<>();//放角色id
    public JSONObject roleinfo=new JSONObject();//角色id和公司账号


    /**
     * 获取之前执行的增加公司的信息
     */
    @BeforeClass
    @SuppressWarnings("unchecked")
    public JSONObject getComsumerinfo(ITestContext ctx){

        //获取增加公司类的数据

        comsumerinfo =(JSONObject) ctx.getAttribute(AddComsumerTest.SERVER_GROUPS);
        return this.comsumerinfo;
    }

    /**
     * 塞Id以便新增用户使用
     * @param ctx
     */
    @AfterClass(alwaysRun = true)
    public void setRoleId(ITestContext ctx) {
        ctx.setAttribute(SERVER_GROUPS, this.roleinfo);
    }

    /**
     * 登录获取header
     */
    @Parameters({"ENV"})
    @BeforeMethod
    public Header[] getHeaders(String ENV){

        this.loginheader = new LoginTest().loginNormal(comsumerinfo.getString("phone"),"8888","123456",1,ENV);


        return loginheader;

    }



    /**
     * 增加角色
     * @param param
     * @param result
     * @return
     */

    @Parameters({"ENV"})
    @Test(dataProvider = "addrole")
    public JSONObject addRole(AddRoleReq param, String result,String ENV) throws IOException {
        log.info("-----------------------------------------开始发起addRole请求--------------------------------------------------------");

        //清理历史数据
        Integer deleteresult = deleteRoleDataByName(param,loginheader,ENV);

        sleep(10);

        JSONObject response=null;

        //发送请求
        try{
            response = addroleservice.addRole(param,result,loginheader, ENV);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //判断返回
        if("success".equals(JSON.getString(response,"massage"))){
            log.info("新增角色成功");
        }

        //检查入库情况
        Integer checkadd= checkAddSuccess(param);
        if (checkadd == 1){
            log.info("插入成功，且name一致，返回状态位为："+ checkadd);
        }

        //获取角色id
        rolelist.add(getIdByname(param));
        roleinfo.put("roleInfoIdList",rolelist);
        //放置公司管理员账号
        roleinfo.put("account",comsumerinfo.getString("phone"));
        //放公司id
        roleinfo.put("comsumerid",comsumerinfo.getIntValue("consumer"));


        log.info("-----------------------------------------结束addRole请求--------------------------------------------------------");

        return this.roleinfo ;
    }

    @DataProvider(name = "addrole")
    public Object[][] addRoleData() throws IOException, DocumentException {

        XmlHandler xmlhandler= new XmlHandler();
        //获取xml文件的结果
        String result=xmlhandler.getresult(this.getClass().getSimpleName(),"addRole","正常测试");
        //获取xml文件的参数
        JSONObject json =xmlhandler.getParam(this.getClass().getSimpleName(),"addRole","正常测试");

        //从xml取值处理list
        String s= json.getString("functionIds");
        List<Integer> list = new StringToList().String2IntList(s);

        //从前置接口获取comsumerId
        Integer comsumerid=comsumerinfo.getInteger("consumer");

        //处理获取的参数
        AddRoleReq addroleparam=new AddRoleReq(comsumerid,list,json.getString("name"),json.getString("remark"));

        //塞进dataprovider

        return  new Object[][]{{

                addroleparam,
                result,
        },

        };

    }

    /**
     * 根据角色名称删除记录
     * @param param
     * @return
     */

    @Parameters({"ENV"})
    @Test
    public  Integer deleteRoleDataByName(AddRoleReq param,Header[] header,String ENV) throws IOException {

        //获取id
        String name = param.getName();
        Integer Id = rmapper.getRoleId(name);

        DeleteRoleReq deleteparam = new DeleteRoleReq(Id);

        //调用删除角色接口
        JSONObject deleteresponse =deleteroleservice.delteRole(deleteparam,"操作成功",header, ENV);

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
    public Integer checkAddSuccess(AddRoleReq request){
        Integer isdelete = 0;
        String responsename = request.getName();
        List<RoleEntity> role =rmapper.getRoleByName(responsename);

        String dataname=role.get(0).getName();
        Assert.assertEquals(dataname,responsename);
        return 1;

    }

    /**
     * 根据name获取套餐id
     * @param request
     * @return
     */

    public Integer getIdByname(AddRoleReq request ){
        String name = request.getName();
        Integer Id = rmapper.getRoleId(name);

        return Id;
    }
}
