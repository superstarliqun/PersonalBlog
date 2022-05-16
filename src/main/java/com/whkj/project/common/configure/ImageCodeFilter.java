package com.whkj.project.common.configure;

import com.alibaba.fastjson.JSONObject;
import com.whkj.project.common.exception.ImageCodeException;
import com.whkj.project.service.serviceImpl.ValidationOfCaptcha;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName ImageCodeFilter.java
 * @Description  通过方法验证，验证码是否正确
 * @Author wuliqun
 * @Date 2022/5/16 11:18
 * @Version 1.0
 */
@Component
public class ImageCodeFilter extends OncePerRequestFilter implements InitializingBean {

    private Set<String> urls = new HashSet<>();

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    ValidationOfCaptcha validation;

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        urls.add("/login");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        boolean action = false;
        String t = httpServletRequest.getRequestURI();
        for (String url : urls) {
            if (antPathMatcher.match(url,httpServletRequest.getRequestURI())){
                action = true;
                break;
            }
        }
        if (action) {
            try {
                /*图片验证码是否正确*/
                checkImageCode(httpServletRequest);
            }catch (ImageCodeException e){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("code", 302);
                jsonObject.put("msg",e.getMessage());
                httpServletResponse.getWriter().write(jsonObject.toJSONString());
                return;
            }
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);

    }

    // 验证码判断
    private void checkImageCode(HttpServletRequest httpServletRequest) {
        // 获取前端数据验证码code
        String captcha = httpServletRequest.getParameter("imageCode");
        // 获取redis中验证码code
        String data = (String) redisTemplate.opsForValue().get("captcha_" + httpServletRequest.getSession().getId());

        validation.check(captcha,data,httpServletRequest);
    }



}
