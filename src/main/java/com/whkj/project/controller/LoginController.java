package com.whkj.project.controller;

import com.whkj.project.utils.MD5Util;
import com.whkj.project.utils.RestResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;


@Controller
public class LoginController {

    @GetMapping(value = "/login")
    public String login1(){
        return "login";
    }


    @PostMapping(value = "/login")
    public String login(String username, String password){
        Subject subject = SecurityUtils.getSubject();
        password = MD5Util.encrypt(username.toLowerCase(), password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        subject.login(token);
        return "success";
    }

    @GetMapping(value = "/index")
    public String index(){
        return "blog/blog_admin/index";
    }
}
