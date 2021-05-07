package com.autoapitest.FunctionService.service.Function;

import com.alibaba.fastjson.JSONObject;
import com.autoapitest.FunctionService.params.Common.Environment;
import com.autoapitest.FunctionService.params.Common.Response;
import com.autoapitest.FunctionService.params.Function.DeleteFunctionReq;
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
 * 删除权限
 *
 * @author wing
 */

@Service
public class DeleteFunctionService extends BasciService {

    String DELETE_FUNCTION_URL="/function/delete";


    /**
     * 携带cookies登录后删除权限
     *
     * @param param
     * @param result
     * @param headers
     * @return
     *
     * @author wing
     */

    public JSONObject deletefunction(DeleteFunctionReq param, String result, Header[] headers, String envir) throws IOException {

        String BASEURL=new GetEnvironment().getUrl(envir,Item);

        //发送请求
        Response response = new GetResponse().postJson(BASEURL+DELETE_FUNCTION_URL,param,null,headers);

        //若返回正常，则将结果转为json对象并比对实际结果和预期，并返回结果
        if(response.getStatusCode()== 200){
            JSONObject json = JSON.fromObject(response.getResponse());
            Tools.assertMsgAndData(json,result);
            return  json;

        }


        //失败则打印失败状态码
        Assert.fail("deletefunction正常接口失败，响应为:" + response.getStatusCode());

        return null;
    }


}
