package com.autoapitest.FunctionService.mapper.item;

import com.autoapitest.FunctionService.entity.UserEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户表相关sql
 * @author wing
 */

public interface UserMapper {

    public List<UserEntity> selectByPhone(@Param("phone")String phone);

    public Integer selectIdByPhone(@Param("phone")String phone);

    public Integer deleteUserByPhone(@Param("phone")String phone);

    public Integer deleteLoginByAccount(@Param("account")String account);

    public Integer deleteRoleByPhone(@Param("phone")String phone);

    public Integer updateUserStatusByPhone(@Param("phone")String phone);

    public Integer getUserStatusByid(@Param("id") Integer id);

    public String getUserPhoneByid(@Param("id")Integer id);


}
