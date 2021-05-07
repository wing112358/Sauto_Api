package com.autoapitest.FunctionService.service.MealAndComsumer;

import com.alibaba.fastjson.JSONObject;
import com.autoapitest.FunctionService.params.Common.Environment;
import com.autoapitest.FunctionService.params.Common.Response;
import com.autoapitest.FunctionService.params.MealAndComsumer.DeleteMealReq;
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
 * 删除套餐
 * @author wing
 */

@Service
public class DeleteMealService extends BasciService {

    String DELETE_MEAL_URL="/setMeal/delete";

    /**
     * 删除套餐接口
     * @param param
     * @param result
     * @param headers
     * @return
     */
    public JSONObject deleteMeal(DeleteMealReq param, String result, Header[] headers, String envir) throws IOException {

        String BASEURL=new GetEnvironment().getUrl(envir,Item);
        //发送请求
        Response response = new GetResponse().postJson(BASEURL+DELETE_MEAL_URL,param,null,headers);

        //若返回正常，则将结果转为json对象并比对实际结果和预期，并返回结果
        if(response.getStatusCode()== 200){
            JSONObject json = JSON.fromObject(response.getResponse());
            Tools.assertMsgAndData(json,result);

            return json;
        }

        //失败则打印错误信息
        Assert.fail("deleteMeal接口请求失败，错误码为："+response.getStatusCode());
        return null;

    }



}
