package com.autoapitest.FunctionService.params.MealAndComsumer;

import com.autoapitest.FunctionService.params.Common.Request;
import lombok.Data;

/**
 * 删除客户接口参数维护
 * @author wing
 */

@Data
public class DeleteConsumerReq extends Request {

    /**
     * 公司id
     */
    private Integer id;

    public DeleteConsumerReq(Integer id) {
        this.id = id;
    }
}
