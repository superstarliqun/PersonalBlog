package com.whkj.project.common.shiro.utils;

import org.apache.shiro.authc.UsernamePasswordToken;
/**
 * @ClassName CustomeToken.java
 * @Description
 *   自定义token 继承UsernamePasswordToken,
 *   账号密码登陆（password） 和 免密登陆（nopassword）
 * @Author wuliqun
 * @Date 2022/5/12 11:10
 * @Version 1.0
 */

public class CustomeToken extends UsernamePasswordToken {

    private static final long serialVersionUID = -2564928913725078138L;

    private LoginType type;


    public CustomeToken() {
        super();
    }

    /**
     * 免密登录
     */
    public CustomeToken(String username) {
        super(username, "", false, null);
        this.type = LoginType.NOPASSWD;
    }

    /**
     * 账号密码登录
     */
    public CustomeToken(String username, String password) {
        super(username, password, false, null);
        this.type = LoginType.PASSWORD;
    }

    public LoginType getType() {
        return type;
    }


    public void setType(LoginType type) {
        this.type = type;
    }
}