package com.whkj.project.common.shiro.utils;

public enum LoginType {
    PASSWORD("password"),
    NOPASSWD("nopassword");
    private String code;// 状态值

    private LoginType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
