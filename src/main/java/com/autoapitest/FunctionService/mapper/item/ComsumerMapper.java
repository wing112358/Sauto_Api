package com.autoapitest.FunctionService.mapper.item;

import com.autoapitest.FunctionService.entity.ComsumerEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 套餐表相关sql
 * @author wing
 */

public interface ComsumerMapper {

    public Integer deleteComsumerByName(@Param("name")String name);

    public Integer deletemealByName(@Param("name")String name);

    public Integer deletefunctionByName(@Param("name")String name);

    public List<ComsumerEntity> getComsumerByName(@Param("name")String name);

    public Integer getIdByname(@Param("name") String name);

}
