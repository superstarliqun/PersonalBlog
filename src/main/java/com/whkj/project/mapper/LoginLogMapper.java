package com.whkj.project.mapper;

import com.whkj.project.entity.LoginLog;
import com.whkj.project.entity.UserEntity;

import java.util.List;

public interface LoginLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LoginLog record);

    int insertSelective(LoginLog record);

    LoginLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LoginLog record);

    int updateByPrimaryKey(LoginLog record);

    List<LoginLog> getLoginLog();

    UserEntity findLoginUserExist(String userName);
}