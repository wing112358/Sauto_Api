package com.autoapitest.FunctionService.params.Login;


import com.autoapitest.FunctionService.params.Common.Request;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 登录
 *
 * @author wing
 */

@XStreamAlias("LoginReq")
@Data
public class LoginReq  extends Request {
    /**
     * 账号
     */

    private String account;

    /**
     * 验证码
     */

    private String code;


    /**
     * 密码
     */

    private String password;


    public LoginReq(String account, String code, String password) {
        this.account = account;
        this.code = code;
        this.password = password;
    }

}
