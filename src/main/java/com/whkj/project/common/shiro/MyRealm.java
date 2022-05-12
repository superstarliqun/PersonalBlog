package com.whkj.project.common.shiro;

import com.whkj.project.common.shiro.utils.CustomeToken;
import com.whkj.project.common.shiro.utils.JwtToken;
import com.whkj.project.entity.MenuEntity;
import com.whkj.project.entity.RoleEntity;
import com.whkj.project.entity.UserEntity;
import com.whkj.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @ClassName MyRealm.java
 * @Description  自定义realm
 * @Author wuliqun
 * @Date 2022/5/12 10:56
 * @Version 1.0
 */
@Slf4j
public class MyRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    /**
     * 设置realm名称
     */
    @Override
    public void setName(String name) {
        super.setName("customRealm");
    }

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

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
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("已经进入用户认证···");

        JwtToken jwtToken = (JwtToken) authenticationToken;
        String token = jwtToken.getToken();
        // 通过用户名到数据库查询用户信息
        UserEntity user = userService.queryUserByName(jwtToken.getUsername());

        //获取用户输入用户名以及密码
        String username = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());

        if (user == null||!StringUtils.equals(password,user.getPassword())) {
            log.error("用户名或者密码验证失败！");
            throw new IncorrectCredentialsException("用户名或密码错误！");
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(jwtToken, user.getPassword(), this.getName());
        //判断是否是免密登录类型
        if (authenticationToken instanceof CustomeToken) {
            return new SimpleAuthenticationInfo("", "", this.getClass().getSimpleName());
        }else{

            if(token == null || StringUtils.isBlank(username)){
                log.error("Token令牌认证失败");
                throw new AuthenticationException("Token令牌认证失败");
            }
            return simpleAuthenticationInfo;
        }
    }

    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        //自定义密码匹配器
        CurrentCredentialsMatcher currentCredentialsMatcher = new CurrentCredentialsMatcher();
        super.setCredentialsMatcher(currentCredentialsMatcher);
    }
}

