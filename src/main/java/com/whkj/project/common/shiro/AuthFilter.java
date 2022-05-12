package com.whkj.project.common.shiro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.whkj.project.common.shiro.utils.JwtToken;
import com.whkj.project.utils.RestResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 自定义过滤器
 */
public class AuthFilter extends AuthenticatingFilter {


    /**
     * 获取token
     *
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws Exception
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        String requestToken = getRequestToken((HttpServletRequest) servletRequest);
        JwtToken jwtToken = new JwtToken(requestToken);
        return jwtToken;
    }

    /**
     * 对跨域提供支持
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

    /**
     * 验证token
     * 当访问拒绝时是否已经处理了；
     * 如果返回true表示需要继续处理；
     * 如果返回false表示该拦截器实例已经处理完成了，将直接返回即可。
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        //完成token登入
        //1.检查请求头中是否含有token
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String token = getRequestToken(httpServletRequest);
        //2. 如果客户端没有携带token，拦下请求
        if (null == token || "".equals(token)) {
            responseTokenError(servletResponse, "Token为空，您无权访问该接口");
            return false;
        }
        //3. 如果有，对进行进行token验证
        return executeLogin(servletRequest, servletResponse);
    }

    /**
     * 执行认证
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        JwtToken jwtToken = (JwtToken) createToken(request, response);
        try {
            SecurityUtils.getSubject().login(jwtToken);
        } catch (AuthenticationException e) {
            responseTokenError(response, "Token无效，您无权访问该接口");
            return false;
        }
        return true;
    }


    /**
     * 获取请求的token
     */
    private String getRequestToken(HttpServletRequest httpRequest) {
        //从header中获取token
        String token = httpRequest.getHeader("token");

        //如果header中不存在token，则从参数中获取token
        if (StringUtils.isEmpty(token)) {
            token = httpRequest.getParameter("token");
        }
        if (StringUtils.isEmpty(token)) {
            Cookie[] cks = httpRequest.getCookies();
            if (cks != null) {
                for (Cookie cookie : cks) {
                    if (cookie.getName().equals("yzjjwt")) {
                        token = cookie.getValue();
                        return token;
                    }
                }
            }
        }
        return token;
    }

    /**
     * 无需转发，直接返回Response信息 Token认证错误
     */
    private void responseTokenError(ServletResponse response, String msg) {
        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
        httpServletResponse.setStatus(HttpStatus.OK.value());
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        try (PrintWriter out = httpServletResponse.getWriter()) {
            ObjectMapper objectMapper = new ObjectMapper();
            String data = objectMapper.writeValueAsString(RestResult.build(401,msg));
            out.append(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}