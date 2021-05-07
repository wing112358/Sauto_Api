package com.autoapitest.FunctionService.mapper.item;

import com.autoapitest.FunctionService.entity.MealEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 套餐表相关sql
 * @author wing
 */

public interface MealMapper {

    public Integer deleteFunctionByName(@Param("name")String name);
    public Integer deleteMealByName(@Param("name")String name);
    public List<MealEntity> getMealByName(@Param("name")String name);
    public Long getIdByname(@Param("name")String name);
}
