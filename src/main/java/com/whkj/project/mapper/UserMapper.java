package com.whkj.project.mapper;

import com.whkj.project.entity.UserEntity;

public interface UserMapper {

    UserEntity queryUserByName(String username);
}