package com.whkj.project.controller.frontend;

import com.whkj.project.service.UserService;
import com.whkj.project.service.LoginLogService;
import com.whkj.project.utils.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
public class LoginController {

    @Autowired
    private LoginLogService loginLogService;

    @Autowired
    private UserService userService;

    /**
     * 登录
     * @return
     */
    @PostMapping(value = "/login")
    public void login(String username,
                        String password,
                        @RequestParam(value = "rememberMe",defaultValue = "false") Boolean rememberMe,
                        @RequestParam(value = "captcha") String captcha,
                        HttpServletRequest request){
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

    /**
     * 查询登录日志列表数据
     */
    @GetMapping(value = "/queryLoginLog")
    public RestResult queryLoginLog(){
        return loginLogService.queryLoginLog();
    }









}
