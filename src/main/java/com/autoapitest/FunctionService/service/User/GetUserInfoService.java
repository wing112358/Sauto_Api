package com.autoapitest.FunctionService.service.User;

import com.alibaba.fastjson.JSONObject;
import com.autoapitest.FunctionService.params.Common.Environment;
import com.autoapitest.FunctionService.params.Common.Response;
import com.autoapitest.FunctionService.service.BasciService;
import com.autoapitest.FunctionService.system.utils.GetEnvironment;
import com.autoapitest.FunctionService.system.utils.GetResponse;
import com.autoapitest.FunctionService.system.utils.JSON;
import com.autoapitest.FunctionService.system.utils.Tools;
import org.apache.http.Header;
import org.springframework.stereotype.Service;
import org.testng.Assert;

import java.io.IOException;

/**
 * 根据token获取用户信息
 * @author wing
 */

@Service
public class GetUserInfoService extends BasciService {

    String GET_USER_INFO_URL="/user/login/getUserByToken";

    /**
     * 获取用户信息接口
     * @param result
     * @param headers
     * @return
     */
    public JSONObject getUserInfo(String result, Header[] headers,  String envir) throws IOException {

        String BASEURL=new GetEnvironment().getUrl(envir,Item);
        //发送请求
        Response response = new GetResponse().get(BASEURL+GET_USER_INFO_URL, null,null,headers);

        //若返回正常，则将结果转为json对象并比对实际结果和预期，并返回结果
        if(response.getStatusCode()== 200){
            JSONObject json = JSON.fromObject(response.getResponse());
            Tools.assertMsgAndData(json,result);

            return json;
        }

        //失败则打印错误信息
        Assert.fail("getUserInfo接口请求失败，错误码为："+response.getStatusCode());
        return null;

    }



}
