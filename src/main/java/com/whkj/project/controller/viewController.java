package com.whkj.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 后台视图跳转控制器
 */
@Controller
public class viewController {


    /**
     * 登录页面
     * @return
     */
    @GetMapping(value = "/login")
    public String login1(){
        return "login";
    }

    /**
     * 系统主页
     * @return
     */
    @GetMapping(value = "/index")
    public String index(){
        return "blog/blog_admin/index";
    }


    /**
     * 登录页面测试编写
     */
    @GetMapping(value = "/logins")
    public String logins(){
        return "blog/blog_admin/login";
    }







}
