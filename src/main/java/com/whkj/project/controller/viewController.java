package com.whkj.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * 后台视图跳转控制器
 */
@Controller
public class viewController {

    /*-------------------------------------------前台页面------------------------------------------------------*/
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
     * 文章首页
     * @return
     */
    @GetMapping(value = "/article")
    public String article(){
        return "blog/blog_view/blog_center";
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


    /**
     * 编辑博客视图
     */
    @GetMapping(value = "/editBlog")
    public String editBlog(){
        return "blog/blog_view/edit_blog";
    }




    /*-------------------------------------------后台页面------------------------------------------------------*/
    /**
     * 文章列表管理页面
     */
    @GetMapping(value = "/admin/article")
    public String adminArticle(){
        return "/blog/blog_admin/tableArticle";
    }









}
