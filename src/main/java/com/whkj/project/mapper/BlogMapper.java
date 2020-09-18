package com.whkj.project.mapper;

import com.github.pagehelper.PageInfo;
import com.whkj.project.entity.Blog;

import java.util.List;

public interface BlogMapper {
    /**
     * delete by primary key
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(Blog record);

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    Blog selectByPrimaryKey(Integer id);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Blog record);

    List<Blog> getArticleList(PageInfo pageInfo);
}