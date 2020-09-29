package com.whkj.project.controller;

import com.github.pagehelper.PageInfo;
import com.whkj.project.entity.Blog;
import com.whkj.project.service.BlogService;
import com.whkj.project.utils.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * 文章列表管理
 */
@RestController
@RequestMapping(value = "/blog")
public class ArticleController {


    @Autowired
    BlogService blogService;

    @PostMapping(value = "/addBlog")
    public ModelAndView addBlog(Blog blog){
        ModelAndView mav = new ModelAndView();
        int i = blogService.insertSelective(blog);
        if(i>0){
            mav.setViewName("redirect:/article");
        }else{
            mav.addObject("msg", "博客添加失败");
        }
        return mav;
    }


    @GetMapping(value = "/getArticleList")
    public RestResult getArticleList(PageInfo pageInfo){
        PageInfo articleList = blogService.getArticleList(pageInfo);
        return RestResult.ok(articleList);
    }
}
