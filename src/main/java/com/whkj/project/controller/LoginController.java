package com.whkj.project.controller;

import com.wf.captcha.utils.CaptchaUtil;
import com.whkj.project.common.handler.exception.MyException;
import com.whkj.project.utils.MD5Util;
import com.whkj.project.utils.RestResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;


@RestController
public class LoginController {


    /**
     * 登录方法
     * @return
     */
    @PostMapping(value = "/login")
    public RestResult login(String username,
                        String password,
                        @RequestParam(value = "rememberMe",defaultValue = "false") Boolean rememberMe,
                        @RequestParam(value = "captcha") String captcha,
                        HttpServletRequest request){
        HttpSession session = request.getSession();
        String attribute = session.getAttribute("captcha_" + session.getId()).toString();
        if (StringUtils.isBlank(captcha)) {
            throw new MyException("请输入验证码");
        }
        if(!attribute.equalsIgnoreCase(captcha)){
            throw new MyException("验证码不一致！");
        }
        Subject subject = SecurityUtils.getSubject();
        password = MD5Util.encrypt(username.toLowerCase(), password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password,rememberMe);
        subject.login(token);
        return RestResult.ok();
    }

    /**
     * 注销方法
     */
    @GetMapping(value = "/logout")
    public RestResult logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return RestResult.ok();
    }








}
