package com.whkj.project.common.security;

import com.whkj.project.entity.UserEntity;
import com.whkj.project.utils.AuthenticationUtil;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName UserAuthenticationProvider.java
 * @Description 自定义登录验证
 * @Author wuliqun
 * @Date 2022/5/13 14:50
 * @Version 1.0
 */
@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String userName = (String) authentication.getPrincipal();
        // 获取表单中输入的密码
        String password = (String) authentication.getCredentials();
        password = AuthenticationUtil.getPasswordAESde(password);
        // 查询用户是否存在
        UserEntity userInfo = null;
        if (userInfo == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        // 我们还要判断密码是否正确，这里我们的密码使用BCryptPasswordEncoder进行加密的
        if (!userInfo.getPassword().equals(AuthenticationUtil.getPassword(password))) {
            throw new BadCredentialsException("密码不正确");
        }

        Set<GrantedAuthority> authorities = new HashSet<>();
        // 查询用户角色
//        List<SysRoleEntity> sysRoleEntityList = sysUserService.selectSysRoleByUserId(userInfo.getUserId());
//        for (SysRoleEntity sysRoleEntity: sysRoleEntityList){
//            authorities.add(new SimpleGrantedAuthority("ROLE_" + sysRoleEntity.getRoleName()));
//        }
        //userInfo.setAuthorities(authorities);
        return new UsernamePasswordAuthenticationToken(userInfo, password, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
