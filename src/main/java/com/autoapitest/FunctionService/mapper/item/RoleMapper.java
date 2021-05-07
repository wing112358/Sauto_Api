package com.autoapitest.FunctionService.mapper.item;

import com.autoapitest.FunctionService.entity.RoleEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 角色表相关sql
 * @author wing
 */

public interface RoleMapper {

    public Integer deleteRoleByName(@Param("name")String name);

    public Integer deleteRoleFunctionByName(@Param("name")String name);

    public List<RoleEntity> getRoleByName(@Param("name")String name);

    public Integer getRoleId(@Param("name")String name);
}
