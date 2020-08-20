package com.whkj.project.service.serviceImpl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.whkj.project.mapper.UserMapper;
import com.whkj.project.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;
}
