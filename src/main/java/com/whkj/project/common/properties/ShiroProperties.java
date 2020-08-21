package com.whkj.project.common.properties;


import lombok.Data;

/**
 * shiro参数文件属性
 */
@Data
public class ShiroProperties {

    /**
     * 白名单的url
     */
    private String anonUrl;

    /**
     * 登录url
     */
    private String loginUrl;

    /**
     * 成功登陆url
     */
    private String successUrl;

    /**
     * 退出url
     */
    private String logoutUrl;

    /**
     *  未授权url
     */
    private String unauthorizedUrl;

    /**
     *  记住我cookie的存储时长
     */
    private int cookieTimeout;



}
