package com.whkj.project.service.serviceImpl;

import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import com.whkj.project.common.properties.ValidateCodeProperties;
import com.whkj.project.entity.MenuEntity;
import com.whkj.project.entity.RoleEntity;
import com.whkj.project.entity.UserEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.whkj.project.mapper.UserMapper;
import com.whkj.project.service.UserService;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;

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
        HttpSession session = request.getSession();
        String key = session.getId();

        ValidateCodeProperties code = new ValidateCodeProperties();

        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        response.setHeader(HttpHeaders.PRAGMA, "No-cache");
        response.setHeader(HttpHeaders.CACHE_CONTROL, "No-cache");
        response.setDateHeader(HttpHeaders.EXPIRES, 0L);

        Captcha captcha = new SpecCaptcha(code.getWidth(), code.getHeight(), code.getLength());
        session.setAttribute("captcha_"+key,captcha.text().toLowerCase());
        captcha.setCharType(code.getCharType());
        captcha.out(response.getOutputStream());
    }
}
