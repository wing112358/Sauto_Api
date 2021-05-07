package com.autoapitest.FunctionService.params.User;


import com.autoapitest.FunctionService.params.Common.Request;
import lombok.Data;

/**
 * 新增角色接口参数维护
 * @author wing
 */

@Data
public class DeleteRoleReq extends Request {

    /**
     * 角色id
     */
    private Integer id;


    public DeleteRoleReq(Integer id) {
        this.id = id;
    }
}
