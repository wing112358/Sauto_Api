package com.autoapitest.FunctionService.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * function表字段维护
 * @author wing
 */

@Data
public class FunctionEntity {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 父id
     */
    private Long pid;

    /**
     * 功能名称
     */
    private String name;

    /**
     * 功能链接，后端为接口路径，前端为访问地址
     */
    private String url;

    /**
     * 功能类型：目录1，页面2，按钮3
     */
    private Integer type;

    /**
     * 排序，数越小越靠前
     */
    private Integer sort;

    /**
     * 状态：1上架，2下架
     */
    private Integer status;

    /**
     * 等级
     */
    private Integer level;

    /**
     * 是否删除：删除1，不删除0
     */
    private Integer is_delete;

    /**
     * 更新时间
     */
    private Timestamp update_time;

    /**
     * 创建时间
     */
    private Timestamp create_time;

    public FunctionEntity(Long id, Long pid,String  name,String  url,Integer type,Integer sort,Integer status,Integer level,Integer is_delete,Timestamp update_time,Timestamp create_time) {
        this.id = id;
        this.name = name;
        this.pid = pid;
        this.type = type;
        this.url = url;
        this.sort = sort;
        this.status = status;
        this.level = level;
        this.is_delete = is_delete;
        this.update_time = update_time;
        this.create_time = create_time;
    }


}
