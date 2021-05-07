package com.autoapitest.FunctionService.params.MealAndComsumer;

import com.autoapitest.FunctionService.params.Common.Request;
import lombok.Data;

/**
 * 新增客户接口参数维护
 * @author wing
 */

@Data
public class AddConsumerReq extends Request {
    /**
     * 管理员账号
     */
    private String account;

    /**
     * 公司id
     */
    private Integer id;

    /**
     * 公司名称
     */
    private String name;

    /**
     * 套餐id
     */
    private Integer setMealId;

    public AddConsumerReq(String account, Integer id, String name, Integer setMealId) {
        this.account = account;
        this.id = id;
        this.name = name;
        this.setMealId = setMealId;
    }
}
