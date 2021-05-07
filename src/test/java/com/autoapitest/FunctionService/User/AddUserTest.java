package com.autoapitest.FunctionService.User;


import com.alibaba.fastjson.JSONObject;
import com.autoapitest.FunctionService.BaseTest;
import com.autoapitest.FunctionService.LoginTest;
import com.autoapitest.FunctionService.entity.UserEntity;
import com.autoapitest.FunctionService.params.Common.Environment;
import com.autoapitest.FunctionService.params.User.AddUserReq;
import com.autoapitest.FunctionService.params.User.DeleteUserReq;
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
 * 新增用户测试方法
 * @author wing
 */

@Slf4j
public class AddUserTest extends BaseTest {

    Header[] loginheader ;
    public static final String SERVER_GROUPS = "SERVER_GROUPS";
    public JSONObject roleinfo = new JSONObject();
    public JSONObject userinfo=new JSONObject();//塞公司id以及用户id以及用户手机号


    /**
     * 获取之前执行的增加角色的id和账号
     */
    @BeforeClass
    @SuppressWarnings("unchecked")
    public JSONObject getIdList(ITestContext ctx){

        //获取增加角色类的数据

        roleinfo = (JSONObject)ctx.getAttribute(AddRoleTest.SERVER_GROUPS);
        return this.roleinfo;
    }

    /**
     * 塞Id以便查询用户使用
     * @param ctx
     */
    @AfterClass(alwaysRun = true)
    public void setMealId(ITestContext ctx) {
        ctx.setAttribute(SERVER_GROUPS, userinfo);
    }

    /**
     * 登录获取header
     */

    @Parameters({"ENV"})
    @BeforeMethod
    public Header[] getHeaders(String ENV){
        //获取公司账号
        String phone = roleinfo.getString("account");

        this.loginheader = new LoginTest().loginNormal(phone,"8888","123456",1,ENV);


        return loginheader;

    }



    /**
     * 增加用户
     * @param param
     * @param result
     * @return
     */


    @Parameters({"ENV"})
    @Test(dataProvider = "adduser")
    public JSONObject addUser(AddUserReq param, String result,String ENV) throws IOException {
        log.info("-----------------------------------------开始发起addUser请求--------------------------------------------------------");

        //清理历史数据
        Integer deleteresult = deleteUserDataByName(param,loginheader,ENV);

        sleep(10);


        JSONObject response=null;

        //发送请求
        try{
            response = adduserservice.addUser(param,result,loginheader, ENV);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //判断返回
        if("success".equals(JSON.getString(response,"massage"))){
            log.info("新增用户成功");
        }

        //检查入库情况
        Integer checkadd= checkAddSuccess(param);
        if (checkadd == 1){
            log.info("插入成功，且name一致，返回状态位为："+ checkadd);
        }


        //往返回塞公司id
        this.userinfo.put("comsumerId",roleinfo.getInteger("comsumerid"));

        //塞登录公司账号
        this.userinfo.put("comsumerphone",roleinfo.getString("account"));
        //往返回塞用户手机号
        this.userinfo.put("userphone",param.getPhone());
        //往返回塞用户id
        Integer userid = getIdByPhone(param);
        this.userinfo.put("userid",userid);

        log.info("-----------------------------------------结束addUser请求--------------------------------------------------------");

        return this.userinfo ;
    }

    @DataProvider(name = "adduser")
    public Object[][] addUserData() throws IOException, DocumentException {

        XmlHandler xmlhandler= new XmlHandler();
        //获取xml文件的结果
        String result=xmlhandler.getresult(this.getClass().getSimpleName(),"addUser","正常测试");
        //获取xml文件的参数
        JSONObject json =xmlhandler.getParam(this.getClass().getSimpleName(),"addUser","正常测试");

        List<Integer> rolelist=(List<Integer>) roleinfo.get("roleInfoIdList");

        //处理获取的参数
         AddUserReq adduserparam=new AddUserReq(json.getString("avatar"),null,roleinfo.getIntValue("comsumerid"),json.getString("name"),json.getString("phone"),null,rolelist,json.getInteger("status"),json.getInteger("type"));

        //塞进dataprovider

        return  new Object[][]{{

                adduserparam,
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
    public  Integer deleteUserDataByName(AddUserReq param,Header[] header,String ENV) throws IOException {
        //获取id
        String Phone = param.getPhone();
        Integer Id = umapper.selectIdByPhone(Phone);

        DeleteUserReq deleteparam = new DeleteUserReq(Id);

        //调用删除公司接口
        JSONObject deleteresponse = deleteuserservice.deleteUser(deleteparam,"操作成功",header, ENV);

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
     * 根据手机号查看是否添加成功
     * @param request
     * @return
     */
    public Integer checkAddSuccess(AddUserReq request){
        Integer isdelete = 0;
        String responsephone = request.getPhone();
        List<UserEntity> user =umapper.selectByPhone(responsephone);

        String dataphone=user.get(0).getPhone();
        Assert.assertEquals(dataphone,responsephone);
        return 1;

    }

    /**
     * 根据name获取用户id
     * @param request
     * @return
     */

    public Integer getIdByPhone(AddUserReq request ){
        String Phone = request.getPhone();
        Integer Id = umapper.selectIdByPhone(Phone);

        return Id;
    }
}
