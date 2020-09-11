package com.whkj.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
     * 个人简历页面
     */
    @GetMapping(value = "/blog")
    public String blog(){
        return "blog/blog_view/blog_center";
    }


    /**
     *个人博客首页
     */
    @GetMapping(value = "/curriculum")
    public String curriculum(){
        return "blog/blog_view/curriculum_vitae";
    }

    /**
     *个人博客首页
     */
    @GetMapping(value = "/feedback")
    public String feedback(){
        return "blog/blog_view/feedback";
    }


    /**
     *个人博客首页
     */
    @GetMapping(value = "/home")
    public String home(){
        return "blog/blog_view/home_page";
    }

    /**
     *个人博客首页
     */
    @GetMapping(value = "/product")
    public String product(){
        return "blog/blog_view/product_center";
    }







}
