package com.autoapitest.FunctionService.params.User;


import com.autoapitest.FunctionService.params.Common.Request;
import lombok.Data;

import java.util.List;

/**
 * 新增角色接口参数维护
 * @author wing
 */

@Data
public class AddRoleReq extends Request {

    /**
     * 公司id
     */
    private Integer customerId;
    /**
     * 权限列表
     */
    private List<Integer> functionIds;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色备注
     */
    private String remark;

    public AddRoleReq(Integer customerId,List<Integer> functionIds,String name,String remark) {
        this.customerId = customerId;
        this.functionIds = functionIds;
        this.name = name;
        this.remark = remark;
    }
}
