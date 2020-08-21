package com.whkj.project.service.serviceImpl;

import com.whkj.project.entity.UserEntity;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.whkj.project.mapper.UserMapper;
import com.whkj.project.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;

    @Override
    public UserEntity queryUserByName(String username) {
        return userMapper.queryUserByName(username);
    }
}
