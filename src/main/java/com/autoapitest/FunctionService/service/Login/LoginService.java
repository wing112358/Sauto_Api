package com.autoapitest.FunctionService.service.Login;


import com.alibaba.fastjson.JSONObject;
import com.autoapitest.FunctionService.params.Common.Environment;
import com.autoapitest.FunctionService.params.Common.Response;
import com.autoapitest.FunctionService.params.Login.LoginReq;
import com.autoapitest.FunctionService.service.BasciService;
import com.autoapitest.FunctionService.system.utils.GetEnvironment;
import com.autoapitest.FunctionService.system.utils.GetResponse;
import com.autoapitest.FunctionService.system.utils.JSON;
import com.autoapitest.FunctionService.system.utils.Tools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.testng.Assert;

import java.io.IOException;

/**
 * 登录服务
 *
 * @author wing
 *
 */
@Slf4j
@Service
public class LoginService  extends BasciService {
    String MANAGER_LOGIN_URL = "/login";
    String USER_LOGIN_URL="/user/login";


    /**
     * 正常登录后获取token
     *
     * @param  param
     * @param  result
     * @param flag   //0 管理员登录   1一般用户登录
     * @return
     *
     */

    public JSONObject login(LoginReq param , String result, int flag,String envir) throws IOException {


        String BASEURL=new GetEnvironment().getUrl(envir,Item);
        String url="";

        if (flag == 0){
            url = BASEURL+MANAGER_LOGIN_URL;

        }else if (flag == 1){
            url = BASEURL+USER_LOGIN_URL;
        }else{
            log.info("flg传值错误");
        }
        //利用通用请求方式发送请求，获取返回
        log.info(url);
        log.info(param.toString());
        Response response = new GetResponse().postJson(url,param,null,null);



        //若返回正常，则把返回转为json
        if (response.getStatusCode()==200){
            JSONObject json = JSON.fromObject(response.getResponse());
            Tools.assertMsgAndData(json,result);

            return json;

        }

        //失败则打印失败状态码
        Assert.fail("login-正常接口失败，响应为:" + response.getStatusCode());

        return null;
    }

}
