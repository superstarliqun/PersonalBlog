package com.whkj.project.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.whkj.project.mapper.BlogMapper;
import com.whkj.project.entity.Blog;
import com.whkj.project.service.BlogService;

import java.util.Date;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService{

    @Resource
    private BlogMapper blogMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return blogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(Blog record) {
        record.setCreateTime(new Date());
        return blogMapper.insertSelective(record);
    }

    @Override
    public Blog selectByPrimaryKey(Integer id) {
        return blogMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Blog record) {
        record.setUpdateTime(new Date());
        return blogMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public PageInfo getArticleList(PageInfo pageInfo) {
        PageHelper.startPage(pageInfo.getPageNum(),pageInfo.getPageSize());
        List<Blog> articleList = blogMapper.getArticleList(pageInfo);
        return new PageInfo(articleList);
    }

}
