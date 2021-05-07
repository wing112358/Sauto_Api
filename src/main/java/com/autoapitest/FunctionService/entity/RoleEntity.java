package com.autoapitest.FunctionService.entity;


import lombok.Data;

/**
 * role_info+role_function表字段维护
 * @author wing
 */
@Data
public class RoleEntity {

    //role_info表

    /**
     *角色id
     */
    private Long role_info_id;
    /**
     *公司id
     */
    private Long customer_id;
    /**
     *角色名称
     */
    private String name;
    /**
     *备注
     */
    private String remark;
    /**
     *是否删除：1 删除0 不删除
     */
    private Integer is_delete;
//    /**
//     *更新时间
//     */
//    private Date role_info_update_time;
//    /**
//     *创建时间
//     */
//    private Date role_info_create_time;

    //role_function表
    /**
     *角色功能关联id
     */
    private Long role_function_id;
    /**
     *功能id
     */
    private Long function_id;
    /**
     *角色id
     */
    private Long role_id;
//    /**
//     *更新时间
//     */
//    private Date role_function_update_time;
//    /**
//     *创建时间
//     */
//    private Date role_function_create_time;


    public RoleEntity(Long role_info_id,Long customer_id,String name,String remark,Integer is_delete,
//            Date role_info_update_time,Date role_info_create_time,
                      Long role_function_id,Long function_id,Long role_id
//                      Date role_function_update_time,Date role_function_create_time
    ) {
        this.role_info_id = role_info_id;
        this.customer_id = customer_id;
        this.name = name;
        this.remark = remark;
        this.is_delete = is_delete;
//        this.role_info_update_time = role_info_update_time;
//        this.role_info_create_time = role_info_create_time;
        this.role_function_id = role_function_id;
        this.function_id = function_id;
        this.role_id = role_id;
//        this.role_function_update_time = role_function_update_time;
//        this.role_function_create_time = role_function_create_time;

    }
}
