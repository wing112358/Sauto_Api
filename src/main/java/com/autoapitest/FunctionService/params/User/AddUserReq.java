package com.autoapitest.FunctionService.params.User;


import com.autoapitest.FunctionService.params.Common.Request;
import lombok.Data;

import java.util.List;

/**
 * 新增用户接口参数维护
 * @author wing
 */

@Data
public class AddUserReq extends Request {

    /**
     * 头像
     */
    private String avatar;
    /**
     * 合作团队
     */
    private String cooperationTeam;
    /**
     * 公司id
     */
    private Integer customerId;
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 用户手机号
     */
    private String phone;
    /**
     * 备注
     */
    private String remark;
    /**
     * 角色列表
     */
    private List<Integer> roleInfoIdList;
    /**
     * 用户状态
     */
    private Integer status;
    /**
     * 用户类型：管理员0，员工1
     */
    private Integer type;

    public AddUserReq(String avatar,String cooperationTeam,Integer customerId,String name,String phone,String remark,List<Integer> roleInfoIdList,Integer status,Integer type) {
        this.cooperationTeam = cooperationTeam;
        this.avatar = avatar;
        this.customerId=customerId;
        this.name=name;
        this.phone=phone;
        this.remark = remark;
        this.roleInfoIdList=roleInfoIdList;
        this.status=status;
        this.type=type;
    }
}
