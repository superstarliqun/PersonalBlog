package com.whkj.project.service;

import com.github.pagehelper.PageInfo;
import com.whkj.project.entity.Blog;
public interface BlogService{


    int deleteByPrimaryKey(Integer id);

    int insertSelective(Blog record);

    Blog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Blog record);

    PageInfo getArticleList(PageInfo pageInfo);
}
