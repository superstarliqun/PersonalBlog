package com.whkj.project.controller.frontend;

import com.whkj.project.entity.Article;
import com.whkj.project.service.ArticleService;
import com.whkj.project.utils.IDRequest;
import com.whkj.project.utils.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName ArticleController.java
 * @Description   文章管理
 * @Author wuliqun
 * @Date 2022/5/18 9:25
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/api/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 列表分页查询
     */
    @GetMapping("/page/{num}/{size}")
    public RestResult page(@PathVariable Integer num,
                           @PathVariable Integer size,
                           String keyWords){
        return articleService.page(num,size,keyWords);

    }

    /**
     * 详情查询
     */
    @GetMapping("/get/{id}")
    public RestResult get(@PathVariable Integer id){
        return articleService.get(id);

    }

    /**
     * 编辑AND新增
     */
    @GetMapping("/saveAndEdit")
    public RestResult saveAndEdit(@RequestBody Article article){
        return articleService.saveAndEdit(article);
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    public RestResult remove(@RequestBody IDRequest id){
        return articleService.remove(id.getId());
    }



}
