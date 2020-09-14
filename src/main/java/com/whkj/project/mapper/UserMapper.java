package com.whkj.project.mapper;

import com.whkj.project.entity.MenuEntity;
import com.whkj.project.entity.RoleEntity;
import com.whkj.project.entity.UserEntity;

import java.util.List;

public interface UserMapper {

    UserEntity queryUserByName(String username);

    List<RoleEntity> findUserRole(String primaryPrincipal);

    List<MenuEntity> findUserMenu(String primaryPrincipal);

    Integer findOpenIdExit(String openid);

    Integer createLoginUser(UserEntity userEntity);

}