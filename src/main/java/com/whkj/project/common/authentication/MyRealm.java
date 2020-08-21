package com.whkj.project.common.authentication;

import com.whkj.project.entity.UserEntity;
import com.whkj.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

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
        return null;
    }

    /**
     * 身份认证方法
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("已经进入用户认证···");
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

