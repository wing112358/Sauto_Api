package com.autoapitest.FunctionService.entity;


import lombok.Data;

/**
 * add_function_test表字段维护
 * @author wing
 */
@Data
public class AddFunctionTestEntity {



    /**
     *记录id
     */
    private Long id;

    /**
     *自身id
     */
    private Long ownid;

    /**
     *父id
     */
    private Long pid;

    /**
     *名称
     */
    private String name;

    /**
     *链接
     */
    private String url;

    /**
     *类型
     */
    private Integer type;

    /**
     *期望
     */
    private String expected;







    public AddFunctionTestEntity(Long id, Long ownid, Long pid, String name, String url, Integer type, String expected) {
        this.id = id;
        this.ownid = ownid;
        this.pid = pid;
        this.name = name;
        this.url = url;
        this.type = type;
        this.expected = expected;
    }
}
