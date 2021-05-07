package com.autoapitest.FunctionService.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * set_meal以及set_meal_function表字段维护
 * @author wing
 */

@Data
public class MealEntity {

    //set_meal表

    /**
     *套餐id
     */
    private Long set_mealid;

    /**
     *套餐名称
     */
    private String  name;

    /**
     *套餐时长
     */
    private String duration;

    /**
     *是否删除：0 未删除，1已删除
     */
    private Integer is_delete;

    /**
     *套餐更新时间
     */
    private Timestamp mael_update_time;

    /**
     *套餐创建时间
     */
    private Timestamp mael_create_time;

    //set_meal_function表
    /**
     *套餐与权限关联id
     */
    private Long sfrelationid;
    /**
     *套餐id
     */
    private Long set_meal_id;
    /**
     *功能id
     */
    private Long function_id;
    /**
     *更新时间
     */
    private Timestamp update_time;
    /**
     *创建时间
     */
    private Timestamp create_time;


    public MealEntity(long set_mealid,String name,String duration,Integer is_delete,Timestamp mael_create_time,Timestamp mael_update_time,long sfrelationid,long set_meal_id,long function_id,Timestamp create_time,Timestamp update_time){
        this.set_mealid=set_mealid;
        this.name=name;
        this.duration=duration;
        this.is_delete=is_delete;
        this.mael_create_time=mael_create_time;
        this.mael_update_time=mael_update_time;
        this.sfrelationid=sfrelationid;
        this.set_meal_id=set_meal_id;
        this.function_id=function_id;
        this.create_time=create_time;
        this.update_time=update_time;

    }
}
