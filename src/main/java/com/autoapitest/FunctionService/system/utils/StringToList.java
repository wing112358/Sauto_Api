package com.autoapitest.FunctionService.system.utils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 字符串转换为list
 * @author wing
 */

public class StringToList {

    /**
     * String用逗号分隔转成list-int
     */

    public List<Integer> String2IntList(String string){

        //去除中括号
        String s=StringUtils.strip(string.toString(),"[]").replaceAll(" ", "");
        List<Integer> intTerms= new ArrayList<>();

        //将字符串转成字符串数组
        List<String> terms = Arrays.asList(StringUtils.split(s, ","));

        //将字符串数组转为int数组

        CollectionUtils.collect(terms, new Transformer() {
            @Override
            public Object transform(Object o) {
                return Integer.valueOf(o.toString());
            }

        }, intTerms);



        return  intTerms;
    }

    /**
     * String用逗号分隔转成list-String
     */

    public List<String> String2StringList(String string){

        String s=string;

        List<String> terms = Arrays.asList(StringUtils.split(s, ","));

        return terms;
    }
}
