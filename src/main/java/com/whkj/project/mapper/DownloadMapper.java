package com.whkj.project.mapper;

import com.github.pagehelper.PageInfo;
import com.whkj.project.entity.Download;

import java.util.List;

public interface DownloadMapper {
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
    int insertSelective(Download record);

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    Download selectByPrimaryKey(Integer id);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Download record);

    List<Download> findAll(PageInfo pageInfo);
}