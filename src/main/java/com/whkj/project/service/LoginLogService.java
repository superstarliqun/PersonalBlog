package com.whkj.project.service;

import com.whkj.project.entity.LoginLog;
import com.whkj.project.entity.UserEntity;

import java.util.List;

public interface LoginLogService{


    int deleteByPrimaryKey(Integer id);

    int insert(LoginLog record);

    int insertSelective(LoginLog record);

    LoginLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LoginLog record);

    int updateByPrimaryKey(LoginLog record);

    List<LoginLog> getLoginLog();
}
