package com.whkj.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 后台视图跳转控制器
 */
@Controller
public class viewController {

    @GetMapping(value = "/login")
    public String login1(){
        return "login";
    }


    @GetMapping(value = "/index")
    public String index(){
        return "blog/blog_admin/index";
    }




}
