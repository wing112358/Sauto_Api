package com.autoapitest.FunctionService.system.utils;

import com.autoapitest.FunctionService.params.Common.Request;
import com.autoapitest.FunctionService.params.Common.Response;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * 基础发送请求方法
 *
 * @author wing
 */
@Slf4j
public class GetResponse {

    //POST-Form
    public Response postForm(String url, Request request, CookieStore cookies, Header[] headers) throws IOException {
        return this.post(url, request, cookies, headers, 0);
    }

    //POST-Json
    public Response postJson(String url, Request request, CookieStore cookies, Header[] headers) throws IOException {
        return this.post(url, request, cookies, headers, 1);
    }
    //POST请求
    public  Response post(String url, Request request,CookieStore cookies,Header[] headers,int flag) throws IOException {

        //用来存放结果
        if(cookies == null){
            cookies = new BasicCookieStore();
        }

        HttpEntity reqentity = getEntity(request,url,flag);

        //构建一个post请求
        HttpPost post = new HttpPost(url);

        //用来执行post方法
        CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(cookies).build();
        //传递参数
        post.setEntity(reqentity);
        //设置header
        post.setHeaders(headers);

        //获取请求header
        Header requestheaders[] = post.getAllHeaders();


        //打印请求
        log.info("这里是请求路径：：："+url);
        log.info("这里是请求header：：：");
        int ii = 0;
        while (ii < requestheaders.length) {
            System.out.println(requestheaders[ii].getName() + ":"
                    + requestheaders[ii].getValue());
            ++ii;
        }


        //预设返回
        CloseableHttpResponse response =null;
        try {
            if (reqentity != null) {
                log.info("此处是请求参数：：：："+IOUtils.toString(reqentity.getContent(), Charset.forName("UTF-8")));
            }
            //获取响应结果
            response = client.execute(post);
            if (response.getEntity() == null) {
                String errMsg = String.format("HTTP请求无返回数据[url=%s,charset=%s]", url, Charset.forName("UTF-8"));
                throw new RuntimeException(errMsg);
            }
            //获取返回内容
            HttpEntity entity = response.getEntity();
            String result1 = IOUtils.toString(entity.getContent(), Charset.forName("UTF-8"));

            //结果转化为json
            Response result = new Response(response.getStatusLine().getStatusCode(), result1, (CookieStore)cookies, response.getAllHeaders());

            return result;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    return null;
    }


    //Get请求
    public Response get(String url, Request request, CookieStore cookies, Header[] headers) throws UnsupportedEncodingException {
        //用来存放结果
        if (cookies == null) {
            cookies = new BasicCookieStore();
        }
        String str="";
        if(request!=null){
            //处理参数与url的拼接

            HttpEntity entity = getEntity(request,url,0);

            //处理参数以及异常
            try {
                str = EntityUtils.toString(entity);
            } catch (IOException | ParseException var16) {
                var16.printStackTrace();
            }

            //拼接url
            url = url + "?" + str;

        }else{
            url=url;
        }

        //发送请求
        CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore((CookieStore)cookies).build();
        HttpGet get = new HttpGet(url);
        //设置header
        get.setHeaders(headers);
        log.info(url);
        CloseableHttpResponse res = null;

        try {
            res = client.execute(get);
            HttpEntity entityresult = res.getEntity();
            String result2 = IOUtils.toString(entityresult.getContent(), Charset.forName("UTF-8"));

            //结果转化为json
            Response result = new Response(res.getStatusLine().getStatusCode(), result2, (CookieStore)cookies, res.getAllHeaders());
            log.info(result.toString());
            return result;
        } catch (IOException var17) {
            var17.printStackTrace();
        }

        return null;
    }



    //根据flag判断是form格式还是json做出不同处理
    private static HttpEntity getEntity(Request request, String url, int flag) throws UnsupportedEncodingException {
        //用来存放结果
        HttpEntity reqentity = null;
        //转化请求为json
        String temp = (new Gson()).toJson(request);

        //置换特殊情况
        temp = temp.replace("\"\"", "null");
        temp = temp.replace("blank", "");

        JsonObject json = (JsonObject)(new JsonParser()).parse(temp);

        //
        if (temp == null) {
            return (HttpEntity)reqentity;
        } else {
            if (flag == 0) {
                List<NameValuePair> params = new ArrayList();
                Iterator it = json.keySet().iterator();

                while(it.hasNext()) {
                    String key = (String)it.next();
                    JsonElement value = json.get(key);
                    if (value.isJsonNull()) {
                        params.add(new BasicNameValuePair(key, (String)null));
                    } else {
                        params.add(new BasicNameValuePair(key, value.getAsString()));
                    }
                }

                reqentity = new UrlEncodedFormEntity(params, Charset.forName("UTF-8"));
            } else {
                reqentity = new StringEntity(json.toString(), Charset.forName("UTF-8"));
                ((StringEntity)reqentity).setContentType("application/json");
            }

            return (HttpEntity)reqentity;
        }


    }
}
