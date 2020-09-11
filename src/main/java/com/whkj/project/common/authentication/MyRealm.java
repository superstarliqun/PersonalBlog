package com.whkj.project.common.authentication;

import com.whkj.project.entity.MenuEntity;
import com.whkj.project.entity.RoleEntity;
import com.whkj.project.entity.UserEntity;
import com.whkj.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author SUPERSTARLIQUN
 */
@Slf4j
public class MyRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    /**
     * 权限认证方法
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("已经进入权限认证···");
        String primaryPrincipal =(String) principals.getPrimaryPrincipal();

        //创建返回实体
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        //查询角色与权限的结果集
        List<RoleEntity> roleList = userService.findUserRole(primaryPrincipal);
        List<MenuEntity> permissionList = userService.findUserMenu(primaryPrincipal);

        //角色与权限都是List<E>集合
        Set<String> roleSet = roleList.stream().map(RoleEntity::getPerms).collect(Collectors.toSet());
        Set<String> permissionSet = permissionList.stream().map(MenuEntity::getPerms).collect(Collectors.toSet());

        //将权限认证放入simpleAuthorizationInfo进行自动任务装配
        simpleAuthorizationInfo.setStringPermissions(permissionSet);
        //将角色认证放入simpleAuthorizationInfo进行自动任务装配
        simpleAuthorizationInfo.setRoles(roleSet);

        return simpleAuthorizationInfo;
    }

    /**
     * 身份认证方法
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("已经进入用户认证···");

        //判断是否是免密登录类型
        if (token instanceof CustomeToken) {
            CustomeToken token1 = (CustomeToken) token;
            UserEntity user = userService.queryUserByName(token1.getUsername());
            if (user != null) {
                return new SimpleAuthenticationInfo(user.getUsername(), "", this.getClass().getSimpleName());
            } else {
                throw new IncorrectCredentialsException("用户名或密码错误！");
            }
        }else{
            //获取用户输入用户名以及密码
            String username = (String) token.getPrincipal();
            String password = new String((char[]) token.getCredentials());
            if(token == null || StringUtils.isBlank(username)){
                log.error("Token令牌认证失败");
                throw new AuthenticationException("Token令牌认证失败");
            }
            // 通过用户名到数据库查询用户信息
            UserEntity user = userService.queryUserByName(username);
            if (user == null||!StringUtils.equals(password,user.getPassword())) {
                log.error("用户名或者密码验证失败！");
                throw new IncorrectCredentialsException("用户名或密码错误！");
                //throw new LockedAccountException("账号已被锁定,请联系管理员！");
            }
            return new SimpleAuthenticationInfo(user.getUsername(), password, ByteSource.Util.bytes(user.getUsername()), getName());
        }
    }
}

