package com.autoapitest.FunctionService.params.User;


import com.autoapitest.FunctionService.params.Common.Request;
import lombok.Data;

/**
 * 新增用户接口参数维护
 * @author wing
 */

@Data
public class DeleteUserReq extends Request {

    /**
     * 员工id
     */
    private Integer id;


    public DeleteUserReq(Integer id) {
        this.id = id;
    }
}
