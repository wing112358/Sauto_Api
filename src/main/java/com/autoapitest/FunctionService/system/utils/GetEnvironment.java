package com.autoapitest.FunctionService.system.utils;

import com.autoapitest.FunctionService.params.Common.Environment;
import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;
import java.util.ResourceBundle;
@Slf4j
public class GetEnvironment {

    public String getUrl(String service,String environment){

        String urlkey=MessageFormat.format("{0}_{0}", service, environment);


        String baseurl="";
        ResourceBundle bundle = ResourceBundle.getBundle("application");

        if (environment.equals("DEV")||environment.equals("Test")||environment.equals("Pre")||environment.equals("Production")){
            baseurl =bundle.getString(urlkey);
            log.info("域名关键字："+urlkey);
            log.info("环境域名："+ baseurl);
        }
        else{
            log.info("环境参数传参错误");
            log.info("这里是传入的环境值："+environment+"这里是传入的系统值："+service);
        }
//        String baseurl="";
//        ResourceBundle bundle = ResourceBundle.getBundle("application");
//
//        switch(envir)
//        {
//            case Dev :
//                String devurlkey= MessageFormat.format("{0}_dev", service);
//                baseurl =bundle.getString(devurlkey);
//                log.info("开发环境域名："+ baseurl);
//                break;
//            case Test :
//                String testurlkey= MessageFormat.format("{0}_test", service);
//                baseurl =bundle.getString(testurlkey);;
//                log.info("测试环境域名："+ baseurl);
//                break;
//            case Pre :
//                String preurlkey= MessageFormat.format("{}_pre", service);
//                baseurl =bundle.getString(preurlkey);
//                log.info("预发环境域名："+ baseurl);
//                break;
//            case Production :
//                String productionurlkey= MessageFormat.format("{0}_production", service);
//                baseurl =bundle.getString(productionurlkey);
//                log.info("生产环境域名："+ baseurl);
//                break;
//            default :
//                log.info("未知环境");
//        }

        return baseurl;
    }
}
