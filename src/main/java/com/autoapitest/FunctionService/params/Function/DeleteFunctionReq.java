package com.autoapitest.FunctionService.params.Function;


import com.autoapitest.FunctionService.params.Common.Request;
import lombok.Data;

import java.util.List;

/**
 * 删除权限接口参数维护
 * @author wing
 */


@Data
public class DeleteFunctionReq extends Request {

    /**
     * 当前id
     */
    private List<Integer> idList;


    public DeleteFunctionReq(List<Integer> idList) {
        this.idList = idList;
    }

}
