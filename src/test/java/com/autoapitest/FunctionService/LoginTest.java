package com.autoapitest.FunctionService;

import com.alibaba.fastjson.JSONObject;
import com.autoapitest.FunctionService.params.Common.Environment;
import com.autoapitest.FunctionService.params.Login.LoginReq;
import com.autoapitest.FunctionService.service.Login.LoginService;
import com.autoapitest.FunctionService.system.utils.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

@Slf4j
public class LoginTest extends BaseTest {

    public JSONObject data;

    /**
     * 登录测试
     *
     *
     */

    @Parameters({"ENV"})
    @Test
    public Header[] loginNormal(String acoount,String code,String password,Integer flag,String ENV) {

        log.info("----------------------------------------开始登录请求---------------------------------------");
        JSONObject response = null;

        LoginReq loginparam = new LoginReq(acoount,code,password);
        String expectedresult="操作成功";

        try {
            //发送登录接口
            response =new LoginService().login(loginparam,expectedresult,flag,ENV);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if ("success".equals(JSON.getString(response, "massage"))) {

            log.info("登录成功");
        }

        data= (JSONObject)response.get("data");
        log.info("次数为返回的data"+ data.toString());
        String cookiename = data.get("tokenHead").toString();
        String cookievalue = data.get("token").toString();
        String token = cookiename + cookievalue;
        log.info("token:", token);

        Header[] header = { new BasicHeader("Authorization", token)};

        log.info("----------------------------------------结束登录请求---------------------------------------");
        return header;



    }




}
