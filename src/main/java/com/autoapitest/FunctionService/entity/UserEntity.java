package com.autoapitest.FunctionService.entity;


import lombok.Data;

/**
 * user_info+user_role表字段维护
 * @author wing
 */
@Data
public class UserEntity {

    //user_info表

    /**
     *用户id
     */
    private Long user_info_id;
    /**
     *公司id
     */
    private Long customer_id;
    /**
     *用户姓名
     */
    private String name;
    /**
     *用户手机号
     */
    private String phone;
    /**
     *用户头像链接
     */
    private String avatar;
    /**
     *用户状态：初始状态0，启用1，停用-1
     */
    private Integer status;
    /**
     *用户类型-管理员0，员工1
     */
    private Integer type;
    /**
     *合作团队
     */
    private String cooperation_team;
    /**
     *用户备注
     */
    private String remark;
    /**
     *是否删除：删除1，不删除0
     */
    private Integer is_delete;
//    /**
//     *更新时间
//     */
//    private Date user_info_update_time;
//    /**
//     *创建时间
//     */
//    private Date user_info_create_time;


    //user_role表
    /**
     *用户与角色关联id
     */
    private Long user_role_id;
    /**
     *角色id
     */
    private Long role_id;
    /**
     *用户id
     */
    private Long user_id;
//    /**
//     *更新时间
//     */
//    private Date user_role_update_time;
//    /**
//     *创建时间
//     */
//    private Date user_role_create_time;

    //login_info表
    /**
     *登录信息id
     */
    private Long login_info_id;
    /**
     *登录用户id
     */
    private Long login_info_user_id;
    /**
     *登录账号
     */
    private String account;
    /**
     *登录密码
     */
    private String password;
    /**
     *是否已经首次登陆（0未首次登陆 1已经首次登陆）
     */
    private Integer already_first_login;
    /**
     *是否删除（删除1，不删除0）
     */
    private Integer login_info_is_delete;




    public UserEntity(Long user_info_id,Long customer_id,String name,String phone,String avatar,Integer status,Integer type,String cooperation_team,String remark,Integer is_delete,
//            Date user_info_update_time,Date user_info_create_time,
                      Long user_role_id,Long role_id,Long user_id
//            , Date user_role_update_time,Date user_role_create_time
                      ,Long login_info_id,Long login_info_user_id,String account,String password,Integer already_first_login,Integer login_info_is_delete
    ) {
        this.user_info_id = user_info_id;
        this.customer_id = customer_id;
        this.name = name;
        this.phone = phone;
        this.avatar = avatar;
        this.status = status;
        this.type = type;
        this.cooperation_team = cooperation_team;
        this.remark = remark;
        this.is_delete = is_delete;
//        this.user_info_update_time = user_info_update_time;
//        this.user_info_create_time = user_info_create_time;
        this.user_role_id = user_role_id;
        this.role_id = role_id;
        this.user_id = user_id;
//        this.user_role_update_time = user_role_update_time;
//        this.user_role_create_time = user_role_create_time;
        this.login_info_id = login_info_id;
        this.login_info_user_id = login_info_user_id;
        this.account = account;
        this.password = password;
        this.already_first_login = already_first_login;
        this.login_info_is_delete = login_info_is_delete;
    }
}
