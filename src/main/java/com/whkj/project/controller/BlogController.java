package com.whkj.project.controller;

import com.github.pagehelper.PageInfo;
import com.whkj.project.entity.Blog;
import com.whkj.project.service.BlogService;
import com.whkj.project.utils.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/blog")
public class BlogController {


    @Autowired
    BlogService blogService;

    @PostMapping(value = "/addBlog")
    public ModelAndView addBlog(Blog blog){
        ModelAndView mav = new ModelAndView();
        int i = blogService.insertSelective(blog);
        if(i>0){
            mav.setViewName("redirect:/blog/getArticleList");
        }else{
            mav.addObject("msg", "博客添加失败");
        }
        return mav;
    }


    @GetMapping(value = "/getArticleList")
    public ModelAndView getArticleList(PageInfo pageInfo){
        ModelAndView mav = new ModelAndView("blog/blog_view/blog_center");
        PageInfo articleList = blogService.getArticleList(pageInfo);
        mav.addObject("collects",articleList);
        return mav;
    }
}
