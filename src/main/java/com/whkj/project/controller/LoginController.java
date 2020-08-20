package com.whkj.project.controller;

import com.whkj.project.utils.RestResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @GetMapping(value = "/login")
    public String login(HttpServletRequest request,
                            @RequestParam(value = "name", defaultValue = "springboot-thymeleaf") String name){
        request.setAttribute("name", name);
        return "login";

    }
}
