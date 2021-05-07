package com.autoapitest.FunctionService.params.Function;


import com.autoapitest.FunctionService.params.Common.Request;
import lombok.Data;
/**
 * 新增权限接口参数维护
 * @author wing
 */


@Data
public class AddFunctionReq  extends Request {

    /**
     * 当前id
     */
    private Integer currentId;
    /**
     * 名称
     */
    private String  name;
    /**
     * 父id
     */
    private Integer pid;


    /**
     * 类型：目录1，页面2，按钮3
     */
    private Integer type;
    /**
     * 链接地址
     */
    private String  url;

    public AddFunctionReq(Integer currentId, String  name, Integer pid,Integer type,String  url) {
        this.currentId = currentId;
        this.name = name;
        this.pid = pid;
        this.type = type;
        this.url = url;
    }

}
