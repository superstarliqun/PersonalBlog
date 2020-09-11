package com.whkj.project.common.authentication;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 自定义token 继承UsernamePasswordToken,
 *  账号密码登陆（password） 和 免密登陆（nopassword）
 */
public class CustomeToken extends UsernamePasswordToken {
    private static final long serialVersionUID = -2564928913725078138L;

    private LoginType type;


    public CustomeToken() {
        super();
    }


    public CustomeToken(String username, String password, LoginType type, boolean rememberMe, String host) {
        super(username, password, rememberMe, host);
        this.type = type;
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