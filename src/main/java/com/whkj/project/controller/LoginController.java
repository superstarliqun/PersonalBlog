package com.whkj.project.controller;

import com.whkj.project.utils.MD5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class LoginController {

    @GetMapping(value = "/login")
    public String login1(){
        return "login";
    }


    @PostMapping(value = "/login")
    public String login(String username,String password){
        Subject subject = SecurityUtils.getSubject();
        password = MD5Util.encrypt(username.toLowerCase(), password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        subject.login(token);
        return "success";
    }
}
