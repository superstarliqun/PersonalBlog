package com.whkj.project.common.handler;


import com.whkj.project.entity.UserEntity;
import com.whkj.project.utils.AuthenticationUtil;
import com.whkj.project.utils.RedisUtil;
import com.whkj.project.utils.RestResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.*;

/**
 * @Description 登录成功处理类
 * @Author SunJ
 * @CreateTime 2019/10/3 9:13
 */
@Slf4j
@Component
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired(required = false)
    private RedisUtil redisUtil;

    /**
     * 登录成功返回结果
     * @Author SunJ
     * @CreateTime 2019/10/3 9:27
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication){
        // 组装JWT
        UserEntity selfUserEntity =  (UserEntity) authentication.getPrincipal();
        String uuid = selfUserEntity.getUserAccount().equals("admin")? "admin":UUID.randomUUID().toString();
        selfUserEntity.setUuid(uuid);


        // 处理登录日志


        String key = AuthenticationUtil.getTokenCode(selfUserEntity.getUserAccount());
        redisUtil.set(key,selfUserEntity,1800);
        // 封装返回参数
        Map<String,Object> resultData = new HashMap<>();
        resultData.put("code","SUCCESS");
        resultData.put("msg", "登录成功");
        resultData.put("user", selfUserEntity);
        resultData.put("token",AuthenticationUtil.getTokenAESen(selfUserEntity.getUserAccount(),uuid));
        RestResult.responseJson(response,resultData);
    }
}
