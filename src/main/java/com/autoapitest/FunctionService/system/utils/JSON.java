package com.autoapitest.FunctionService.system.utils;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Json工具
 *
 * @author wing
 */
@Slf4j
public class JSON {

   public  List<Integer> keylist = new ArrayList<>();

    public static String getString(JSONObject json, String key) {
        if (json == null)
            return null;
        if (json.get(key) == null)
            return null;

        else
            return json.get(key).toString();
    }

    public static Integer getInt(JSONObject json, String key) {
        if (json == null)
            return null;
        if (json.get(key) == null )
            return null;
        else
            return (Integer)json.get(key);
    }

    public static JSONArray getJsonArray(JSONObject json, String key) {
        if (json == null)
            return null;
        if (json.get(key) == null )
            return null;
        else
            return (JSONArray)json.get(key);
    }

    public static JsonObject getJsonObject(JsonObject json, String key) {
        if (json == null)
            return null;
        if (json.get(key) == null || json.get(key).isJsonNull())
            return null;
        else
            return json.get(key).getAsJsonObject();
    }

    public static <T> List<T> objToList(Object obj, Class<T> cla) {
        List<T> list = new ArrayList<T>();
        if (obj instanceof ArrayList<?>) {
            for (Object o : (List<?>) obj) {
                list.add(cla.cast(o));
            }
            return list;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static List<Integer> objToinList(Object obj ) {
        List<Integer> list = new ArrayList<Integer>();
        if (obj instanceof ArrayList<?>) {
            for (Object o : (List<?>) obj) {
                list.add(int.class.cast(o));
            }
            return list;
        }
        return null;
    }


//    public static JsonObject fromObject(Object object) {
//        return (JsonObject) new JsonParser().parse(new Gson().toJson(object));
//    }

    public static JSONObject fromObject(String text) {
        return JSONObject.parseObject(text);
    }


    //根据关键字获取Jsonarry-json内部对应的值

    public  List<Integer> getWithKey(JSONArray json, String key){



        for (Iterator<Object> iterator = json.iterator(); iterator.hasNext(); ) {
            JSONObject next = (JSONObject) iterator.next();

                Integer id = next.getInteger(key);
                this.keylist.add(id);
                JSONArray children =next.getJSONArray("children");
                if (children!=null){
                    this.keylist=getWithKey(children,key);

                }
                log.info("已解析到底");
        }

        log.info("解析结果"+this.keylist.toString());
        return this.keylist;


    }





}
