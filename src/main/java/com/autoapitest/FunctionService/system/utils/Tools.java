package com.autoapitest.FunctionService.system.utils;

import com.alibaba.fastjson.JSONObject;
import org.testng.Assert;

/**
 *
 * 工具类
 *
 * @author wing
 */

public class Tools {

    public static String retrunNullStringIfEmpty(String s) {
        return (s == null || "".equals(s) || "blank".equals(s)) ? "null" : s;
    }

    public static String retrunNullIfEmpty(String s) {
        return (s == null || "".equals(s) || "blank".equals(s)) ? null : s;
    }

    public static String retrunBlankIfEmpty(String s) {
        return (s == null || "".equals(s) || "blank".equals(s)) ? "" : s;
    }

    public static String returnBirthDay(String certNo) {
        return new StringBuilder().append(certNo.substring(6, 10)).append("-").append(certNo.substring(10, 12))
                .append("-").append(certNo.substring(12, 14)).toString();
    }

    public static Integer returnGender(String certNo) {
        int a = Integer.valueOf(certNo.substring(16, 17));
        return a % 2;
    }

    public static void assertResultAndMsg(JSONObject response, String result) {
        if (!"success".equals(result)) {
            Assert.assertEquals(JSON.getString(response, "msg"), result);
        } else {
            Assert.assertEquals(JSON.getString(response, "result"), result, JSON.getString(response, "msg"));
        }
    }

    public static void assertResultAndContainsMsg(JSONObject response, String result) {
        if (!"success".equals(result)) {
            Assert.assertTrue(JSON.getString(response, "msg").contains(result),
                    String.format("结果msg中没有包含期望结果， %s:%s", JSON.getString(response, "msg"), result));
        } else {
            Assert.assertEquals(JSON.getString(response, "result"), result);
        }
    }

    public static void assertMsgAndData(JSONObject response, String result) {
        if (JSON.getString(response, "result") == null) {
            Assert.assertTrue(response.toString().contains(result),
                    String.format("全文 %s 不包含 %s", response.toString(), result));
        } else {
            Assert.assertEquals(JSON.getString(response, "msg"), result);
        }

    }

    /**
     * Long转Integer
     */

    public Integer longToInteger(Long param){
        Long longparam=param;
        Integer inparam= longparam.intValue();

        return inparam;

    }
}
