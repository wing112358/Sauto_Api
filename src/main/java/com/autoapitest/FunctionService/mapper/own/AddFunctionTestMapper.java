package com.autoapitest.FunctionService.mapper.own;

import org.apache.ibatis.annotations.Param;

/**
 * 新增套餐表相关sql方法
 *
 */

public interface AddFunctionTestMapper {
    public Integer InsertDetails(@Param("ownid")long ownid,@Param("pid")long pid,@Param("name")String name,@Param("url")String url,@Param("type")Integer type,@Param("expected")String expected);

}
