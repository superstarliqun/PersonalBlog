package com.whkj.project.common.configure;


import com.whkj.project.entity.UserEntity;
import com.whkj.project.utils.AuthenticationUtil;
import com.whkj.project.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Description 权限处理类
 * @Author SunJ
 * @CreateTime 2019/10/3 8:39
 */
@Slf4j
@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private RedisUtil redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenHeader = request.getHeader(AuthenticationUtil.getTokenHeader());
        if (!StringUtils.isEmpty(tokenHeader)) {
            try {
                String[] tokenAESde = AuthenticationUtil.getTokenAESde(tokenHeader);
                // 获取用户名
                String usernameMd5 = tokenAESde[1];
                Object o = redisTemplate.get(usernameMd5);
                if (null != o){
                    UserEntity user = (UserEntity) o;
                    if(user.getUuid().equals(tokenAESde[0])){
                        // 获取角色
                        String userId=user.getId()+"";
                        List<GrantedAuthority> authorities = (List<GrantedAuthority>) user.getAuthorities();
                        redisTemplate.set(usernameMd5,user,1800);
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, userId, authorities);
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }else {
                }
            } catch (Exception e) {
                log.info("Token无效");
            }
        }
        filterChain.doFilter(request, response);
        return;
    }
}
