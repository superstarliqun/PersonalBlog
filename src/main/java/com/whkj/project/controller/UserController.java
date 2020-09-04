package com.whkj.project.controller;

import com.whkj.project.service.UserService;
import com.whkj.project.utils.RestResult;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 查询用户详情
     * @return
     */
    @GetMapping(value = "/findOneUserDetails")
    @RequiresRoles("administrator")
    public RestResult findOneUserDetails(){
        return RestResult.ok();
    }

    /**
     * 生成验证码
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping(value = "/generateImages")
    public void generateImages(HttpServletRequest request, HttpServletResponse response) throws IOException {
        userService.generateImages(request,response);
    }





}
