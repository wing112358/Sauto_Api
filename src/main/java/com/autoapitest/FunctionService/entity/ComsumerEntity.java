package com.autoapitest.FunctionService.entity;


import lombok.Data;

import java.sql.Timestamp;

/**
 * customer_info+customer_set_meal+customer_function表字段维护
 * @author wing
 */
@Data
public class ComsumerEntity {

    //customer_info表

    /**
     *公司id
     */
    private Long customer_info_id;
    /**
     *公司名称
     */
    private String name;
    /**
     *公司管理员账号-手机号
     */
    private String account;
    /**
     *是否删除：1 已删除 0 未删除
     */
    private Integer is_delete;
//    /**
//     *公司更新时间
//     */
//    private Date customer_info_update_time;
//    /**
//     *公司创建时间
//     */
//    private Date customer_info_create_time;


    //customer_set_meal表

    /**
     *客户套餐关联id
     */
    private Long customer_set_meal_id;
    /**
     *客户id
     */
    private Long customer_id;
    /**
     *套餐id
     */
    private Long set_meal_id;
    /**
     *状态（1生效，2不生效
     */
    private Integer status;
    /**
     *超时时间
     */
    private Timestamp timeout;
//    /**
//     *记录更新时间
//     */
//    private Date customer_set_meal_update_time;
//    /**
//     *记录创建时间
//     */
//    private Date customer_set_meal_create_time;



    //customer_function表

    /**
     *客户与功能关联表
     */
    private Long customer_function_id;
    /**
     *客户id
     */
    private Long customer_function_customer_id;
    /**
     *功能id
     */
    private Long function_id;
//    /**
//     *记录更新时间
//     */
//    private Date customer_function_update_time;
//    /**
//     *记录创建时间
//     */
//    private Date customer_function_create_time;


    public ComsumerEntity(Long customer_info_id,String name,String account,Integer is_delete,
                          Long customer_set_meal_id,Long customer_id,Long set_meal_id,Integer status,Timestamp timeout,
                          Long customer_function_id,Long customer_function_customer_id,Long function_id) {
        this.customer_info_id = customer_info_id;
        this.name = name;
        this.account = account;
        this.is_delete = is_delete;
//        this.customer_info_update_time = customer_info_update_time;
//        this.customer_info_create_time = customer_info_create_time;
        this.customer_set_meal_id = customer_set_meal_id;
        this.customer_id = customer_id;
        this.set_meal_id = set_meal_id;
        this.status = status;
        this.timeout = timeout;
//        this.customer_set_meal_update_time = customer_set_meal_update_time;
//        this.customer_set_meal_create_time = customer_set_meal_create_time;
        this.customer_function_id = customer_function_id;
        this.customer_function_customer_id = customer_function_customer_id;
        this.function_id = function_id;
//        this.customer_function_update_time = customer_function_update_time;
//        this.customer_function_create_time = customer_function_create_time;




    }
}
