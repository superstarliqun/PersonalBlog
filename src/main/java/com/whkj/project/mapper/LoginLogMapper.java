package com.whkj.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whkj.project.entity.LoginLog;
import com.whkj.project.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface LoginLogMapper extends BaseMapper<LoginLog> {

    int deleteByPrimaryKey(Integer id);

    int insert(LoginLog record);

    int insertSelective(LoginLog record);

    LoginLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LoginLog record);

    int updateByPrimaryKey(LoginLog record);

    List<LoginLog> getLoginLog();

    UserEntity findLoginUserExist(String userName);
}