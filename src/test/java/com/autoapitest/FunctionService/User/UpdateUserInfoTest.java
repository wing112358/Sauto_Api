package com.autoapitest.FunctionService.User;


import com.alibaba.fastjson.JSONObject;
import com.autoapitest.FunctionService.BaseTest;
import com.autoapitest.FunctionService.LoginTest;
import com.autoapitest.FunctionService.params.Common.Environment;
import com.autoapitest.FunctionService.params.User.UpdateUserReq;
import com.autoapitest.FunctionService.system.utils.JSON;
import com.autoapitest.FunctionService.system.utils.XmlHandler;
import com.sun.org.apache.xerces.internal.impl.dv.dtd.ENTITYDatatypeValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.dom4j.DocumentException;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.IOException;


/**
 * 更新用户信息测试方法
 */
@Slf4j
public class UpdateUserInfoTest extends BaseTest {
    Header[] loginheader ;
    public static final String SERVER_GROUPS = "SERVER_GROUPS";
    public JSONObject userinfo = new JSONObject();
    String  userphone;

    /**
     * 获取之前执行的增加角色的id和账号
     */
    @BeforeClass
    @SuppressWarnings("unchecked")
    public JSONObject getIdList(ITestContext ctx){

        //获取增加角色类的数据

        userinfo = (JSONObject)ctx.getAttribute(AddUserTest.SERVER_GROUPS);
        return this.userinfo;
    }

    /**
     * 塞Id以便查询用户使用
     * @param ctx
     */

    @AfterClass(alwaysRun = true)
    public void setUpdateFlag(ITestContext ctx) {
        ctx.setAttribute(SERVER_GROUPS, userphone);
    }

    /**
     * 登录获取header
     */

    @Parameters({"ENV"})
    @BeforeMethod
    public Header[] getHeaders(String ENV){
        //获取公司账号
        String phone = userinfo.getString("comsumerphone");

        this.loginheader = new LoginTest().loginNormal(phone,"8888","123456",1, ENV);


        return loginheader;

    }
    /**
     * 更新用户信息
     * @param param
     * @param result
     * @return
     */


    @Parameters({"ENV"})
    @Test(dataProvider = "updateuserinfo",groups = "updateuserinfo")
    public String updateUserinfo(UpdateUserReq param,String result,String ENV) throws InterruptedException {
        log.info("-----------------------------------------开始发起updateUserinfo请求--------------------------------------------------------");

        sleep(10);


        JSONObject response=null;

        //发送请求
        try{
            response = updateuserinfoservice.updateUserInfo(param,result,loginheader, ENV);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //判断返回
        if("success".equals(JSON.getString(response,"massage"))){
            log.info("更新用户成功");
        }

        //检查用户状态
        Integer userstatus= GetUserStatus();
        Assert.assertEquals((int)userstatus,1);

        this.userphone = GetUserPhoneById();

        log.info("-----------------------------------------结束updateUserinfo请求--------------------------------------------------------");

        return this.userphone;

    }

    @DataProvider(name = "updateuserinfo")
    public Object[][] updateUserinfoData() throws IOException, DocumentException {
        XmlHandler xmlhandler= new XmlHandler();
        //获取xml文件的结果
        String result=xmlhandler.getresult(this.getClass().getSimpleName(),"updateUserinfo","正常测试");
        //获取xml文件的参数
        JSONObject json =xmlhandler.getParam(this.getClass().getSimpleName(),"updateUserinfo","正常测试");


        //处理获取的参数
        Integer comsumerid= userinfo.getInteger("comsumerId");
        Integer userid =userinfo.getInteger("userid") ;
        UpdateUserReq updateuserparam=new UpdateUserReq(null,null,comsumerid,userid,null,null,null,null,json.getInteger("status"),null);

        //塞进dataprovider

        return  new Object[][]{{

                updateuserparam,
                result,
        },

        };


    }


    public Integer GetUserStatus(){

        Integer userid = userinfo.getInteger("userid");
        Integer userstatus= umapper.getUserStatusByid(userid);

        return userstatus;
    }

    public String  GetUserPhoneById(){

        Integer userid = userinfo.getInteger("userid");
        String phone= umapper.getUserPhoneByid(userid);

        return phone;
    }
}
