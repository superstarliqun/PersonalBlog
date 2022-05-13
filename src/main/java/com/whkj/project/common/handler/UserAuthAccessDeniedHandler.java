package com.whkj.project.common.handler;

import com.whkj.project.utils.RestResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description 暂无权限处理类
 * @Author SunJ
 * @CreateTime 2019/10/3 8:39
 */
@Component
public class UserAuthAccessDeniedHandler implements AccessDeniedHandler {
    /**
     * 暂无权限返回结果
     * @Author SunJ
     * @CreateTime 2019/10/3 8:41
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception) throws IOException {
        RestResult.responseJson(response,RestResult.resultCode(403,"未授权"));
    }
}