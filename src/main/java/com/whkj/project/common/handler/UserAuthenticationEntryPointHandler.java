package com.whkj.project.common.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户未登录处理类
 * @Author SunJ
 * @CreateTime 2019/10/3 8:55
 */
@Component
public class UserAuthenticationEntryPointHandler implements AuthenticationEntryPoint {
    /**
     * 用户未登录返回结果
     * @Author SunJ
     * @CreateTime 2019/10/3 9:01
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        response.sendError(401,"未登录");
    }
}