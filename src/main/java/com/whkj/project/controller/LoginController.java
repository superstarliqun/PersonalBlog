package com.whkj.project.controller;

import com.wf.captcha.utils.CaptchaUtil;
import com.whkj.project.common.handler.exception.MyException;
import com.whkj.project.utils.MD5Util;
import com.whkj.project.utils.RestResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;


@Controller
public class LoginController {

    @GetMapping(value = "/login")
    public String login1(){
        return "login";
    }


    @PostMapping(value = "/login")
    public String login(String username,
                        String password,
                        @RequestParam(value = "rememberMe",defaultValue = "false") Boolean rememberMe,
                        @RequestParam(value = "captcha") String captcha,
                        HttpServletRequest request){
        HttpSession session = request.getSession();
        String attribute = session.getAttribute("captcha_" + session.getId()).toString();
        if(!attribute.equals(captcha)){
            throw new MyException("验证码不一致！");
        }

        Subject subject = SecurityUtils.getSubject();
        password = MD5Util.encrypt(username.toLowerCase(), password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password,rememberMe);
        subject.login(token);
        return "blog/blog_admin/index";
    }

    /**
     * 跳转后台系统主页
     */
    @GetMapping(value = "/index")
    public String index(){
        return "blog/blog_admin/index";
    }
}
