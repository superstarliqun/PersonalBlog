package com.whkj.project.common.service;


import com.whkj.project.common.handler.exception.MyException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * 验证码效验接口
 */
@Service
@RequiredArgsConstructor
public class ValidationOfCaptcha {


    @Autowired
    private final RedisTemplate redisTemplate;

    public void check(String key, String code, HttpServletRequest request){
        redisTemplate.delete("captcha_" + request.getSession().getId());
        if(StringUtils.isEmpty(key)){
            throw new MyException("验证码不能为空！");
        }
        if(code==null){
            throw new MyException("验证码已失效！");
        }
        if (!StringUtils.equalsIgnoreCase(key, code)) {
            throw new MyException("验证码不正确");
        }


    }


}
