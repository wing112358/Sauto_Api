package com.autoapitest.FunctionService.mapper.item;

import com.autoapitest.FunctionService.entity.FunctionEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 权限表相关sql
 * @author wing
 */

public interface FunctionMapper {

    public List<FunctionEntity> getListByUrl(@Param("url")String  url, @Param("is_delete")Integer is_delete);

    public List<FunctionEntity> getIfExitByStatus();

    public Integer deleteByUrl(@Param("url") String url);

    public Integer getPidByUrl(@Param("url")String url);




}
