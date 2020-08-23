package com.whkj.project.service;

import com.whkj.project.entity.MenuEntity;
import com.whkj.project.entity.RoleEntity;
import com.whkj.project.entity.UserEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface UserService{

    //shiro用户认证是否存在
    UserEntity queryUserByName(String username);

    //shiro权限认证角色结果集
    List<RoleEntity> findUserRole(String primaryPrincipal);

    //shiro权限认证菜单结果集
    List<MenuEntity> findUserMenu(String primaryPrincipal);

    void generateImages(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
