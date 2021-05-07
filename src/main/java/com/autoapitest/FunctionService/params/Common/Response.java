package com.autoapitest.FunctionService.params.Common;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.client.CookieStore;

import java.util.Arrays;

/**
 * 接口返回
 * @author wing
 */


@Data
@Slf4j
public class Response {
    private int statusCode;
    private String response;
    private CookieStore cookies ;
    private Header[] headers;


    public Response(int statusCode, String response, CookieStore cookies, Header[] headers) {
        this.statusCode = statusCode;
        this.response = response;
        this.cookies = cookies;
        this.headers = headers;
        log.info("返回的状态码statusCode:" + statusCode);
        log.info("返回内容response:" + response);
        log.info("返回cookies:" + cookies.toString());
        log.info("返回headers:" + Arrays.deepToString(headers));
    }

}
