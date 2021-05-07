package com.autoapitest.FunctionService.params.MealAndComsumer;

import com.autoapitest.FunctionService.params.Common.Request;
import lombok.Data;

import java.util.List;

/**
 * 新增套餐接口参数维护
 * @author wing
 */

@Data
public class AddMealReq extends Request {

    /**
     * 时长,枚举值：一个月、三个月、半年、一年、三年、永久
     */
    private  String duration;
    /**
     * 套餐包含功能列表
     */
    private List<Integer> functionIdList;
    /**
     * 套餐id
     */
    private  Integer id ;
    /**
     * 套餐名称
     */

    private String name;

    public AddMealReq(String duration, List<Integer> functionIdList, Integer id, String name){
        this.duration=duration;
        this.functionIdList=functionIdList;
        this.id=id;
        this.name=name;
    }
}
