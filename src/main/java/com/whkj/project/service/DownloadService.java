package com.whkj.project.service;

import com.github.pagehelper.PageInfo;
import com.whkj.project.entity.Download;

import java.util.List;

public interface DownloadService{


    int deleteByPrimaryKey(Integer id);

    int insertSelective(Download record);

    Download selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Download record);

    PageInfo findAll(PageInfo pageInfo);

}
