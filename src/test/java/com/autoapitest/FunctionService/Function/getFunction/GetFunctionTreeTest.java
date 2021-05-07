package com.autoapitest.FunctionService.Function.getFunction;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.autoapitest.FunctionService.BaseTest;
import com.autoapitest.FunctionService.LoginTest;
import com.autoapitest.FunctionService.User.UpdateUserInfoTest;
import com.autoapitest.FunctionService.params.Common.Environment;
import com.autoapitest.FunctionService.system.utils.CompareArryList;
import com.autoapitest.FunctionService.system.utils.JSON;
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
 * 获取用户信息测试方法
 * @author wing
 */

@Slf4j
public class GetFunctionTreeTest extends BaseTest {

    Header[] loginheader;
    public String userphone;


    /**
     * 获取之前执行的增加的用户手机号
     */
    @BeforeClass
    @SuppressWarnings("unchecked")
    public String getuserAccount(ITestContext ctx) {

        //获取增加用户类的数据

        userphone = (String)ctx.getAttribute(UpdateUserInfoTest.SERVER_GROUPS);
        return this.userphone;
    }


    /**
     * 登录获取header
     */

    @Parameters({"ENV"})
    @BeforeMethod
    public Header[] getHeaders(String ENV) {


//        String account = this.userinfo.getString("userphone");
        this.loginheader = new LoginTest().loginNormal(this.userphone, "8888", "123456", 1,ENV);


        return loginheader;

    }


    /**
     * 获取用户权限
     *
     * @param result
     * @return
     */

    @Parameters({"ENV"})
    @Test(dataProvider = "getfunctioninfo",dependsOnGroups = "updateuserinfo")
    public void getFunctionInfo(String result,String ENV) {
        log.info("-----------------------------------------开始发起getFunctionInfo请求--------------------------------------------------------");


        JSONObject response = null;

        //发送请求
        try {
            response = getfunctionservice.getfunctiontree(result, loginheader, ENV);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //判断返回
        if ("success".equals(JSON.getString(response, "massage"))) {
            log.info("查询用户信息成功");
        }


        //获取返回functionlist
        JSONArray data=JSON.getJsonArray(response,"data");

        //组装返回的functionlist
        List<Integer> getfunctionIds = new ArrayList<>();

        getfunctionIds=new JSON().getWithKey(data,"id");

        //获取期望的functionlist
        List<Integer> expectedfunctionIds = new ArrayList<>();
        expectedfunctionIds.add(281);
        expectedfunctionIds.add(282);
        expectedfunctionIds.add(283);
        log.info("期望的功能列表：：："+expectedfunctionIds.toString());

        //比对信息
        boolean flag= CompareArryList.compareIntList(expectedfunctionIds,getfunctionIds);
        Assert.assertTrue(flag,"权限不一致");

        log.info("-----------------------------------------结束getFunctionInfo请求--------------------------------------------------------");


    }

    @DataProvider(name = "getfunctioninfo")
    public Object[][] getFunctionInfoData() throws IOException, DocumentException {

        XmlHandler xmlhandler= new XmlHandler();
        //获取xml文件的结果
        String result=xmlhandler.getresult(this.getClass().getSimpleName(),"getFunctionInfo","正常测试");
        return  new Object[][]{{

                result,
        },

        };

    }




//    public  Integer updateUserStatus(){
//
//        Integer updateuserresult = umapper.updateUserStatusByPhone(this.useraccount);
//        log.info("更新状态标志：：："+updateuserresult);
//
//        return updateuserresult;
//    }
}


