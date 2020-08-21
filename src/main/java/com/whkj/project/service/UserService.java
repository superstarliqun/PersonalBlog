package com.whkj.project.service;

import com.whkj.project.entity.UserEntity;

public interface UserService{


    UserEntity queryUserByName(String username);
}
