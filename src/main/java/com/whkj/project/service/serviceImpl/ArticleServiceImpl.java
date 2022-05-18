package com.whkj.project.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.whkj.project.common.configure.UserRequestUtil;
import com.whkj.project.entity.Article;
import com.whkj.project.entity.UserEntity;
import com.whkj.project.mapper.ArticleMapper;
import com.whkj.project.service.ArticleService;
import com.whkj.project.utils.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {


    @Autowired
    private ArticleMapper articleMapper;


    @Override
    public RestResult page(Integer num, Integer size, String keyWords) {
        IPage<Article> articleIPage = null;
        try {
            UserEntity loginUser = UserRequestUtil.getLoginUser();
            LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper();
            if (null != keyWords && !keyWords.equals("")) {
                queryWrapper.and(wrapper -> wrapper.like(Article::getTitle, keyWords));
            }
            articleIPage = articleMapper.selectPage(new Page<>(num, size), queryWrapper);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RestResult.ok(articleIPage);
    }

    @Override
    public RestResult get(Integer id) {
        return RestResult.ok(articleMapper.selectById(id));
    }

    @Override
    public RestResult saveAndEdit(Article article) {
        try {
            if(article.getId()==null||article.getId()==0){
                articleMapper.insert(article);
            }else{
                articleMapper.updateById(article);
            }
            return RestResult.ok();
        } catch (Exception e) {
            return RestResult.build(500,"失败");
        }
    }

    @Override
    public RestResult remove(Integer id) {
        try {
            articleMapper.deleteById(id);
            return RestResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return RestResult.build(500,"删除失败！");
        }
    }
}
