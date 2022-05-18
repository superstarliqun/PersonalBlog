package com.whkj.project.service.serviceImpl;

import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import com.whkj.project.utils.ValidateCodeProperties;
import com.whkj.project.entity.MenuEntity;
import com.whkj.project.entity.RoleEntity;
import com.whkj.project.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.whkj.project.mapper.UserMapper;
import com.whkj.project.service.UserService;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;

    @Autowired
    RedisTemplate redisTemplate;

     @Override
    public UserEntity queryUserByName(String username) {
        return userMapper.queryUserByName(username);
    }

    @Override
    public List<RoleEntity> findUserRole(String primaryPrincipal) {
        return userMapper.findUserRole(primaryPrincipal);
    }

    @Override
    public List<MenuEntity> findUserMenu(String primaryPrincipal) {
        return userMapper.findUserMenu(primaryPrincipal);
    }

    @Override
    public void generateImages(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ValidateCodeProperties code = new ValidateCodeProperties();
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        response.setHeader(HttpHeaders.PRAGMA, "No-cache");
        response.setHeader(HttpHeaders.CACHE_CONTROL, "No-cache");
        response.setDateHeader(HttpHeaders.EXPIRES, 0L);
        Captcha captcha = new SpecCaptcha(code.getWidth(), code.getHeight(), code.getLength());
        redisTemplate.opsForValue().set("captcha_"+request.getSession().getId(),captcha.text().toLowerCase(),1, TimeUnit.MINUTES);
        captcha.setCharType(code.getCharType());
        captcha.out(response.getOutputStream());
    }

    @Override
    public Integer findOpenIdExit(String openid) {
        return userMapper.findOpenIdExit(openid);
    }

    @Override
    public Integer createLoginUser(UserEntity userEntity) {
        return userMapper.createLoginUser(userEntity);
    }
}
