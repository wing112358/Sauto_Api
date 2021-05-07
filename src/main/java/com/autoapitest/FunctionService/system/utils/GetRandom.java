package com.autoapitest.FunctionService.system.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 获取随机数
 * @author wing
 */

public class GetRandom {

    public String getDate(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        String Stringdata = df.format(new Date());

        return Stringdata;
    }
}
