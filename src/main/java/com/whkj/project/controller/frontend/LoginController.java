package com.whkj.project.controller.frontend;

import com.whkj.project.service.serviceImpl.ValidationOfCaptcha;
import com.whkj.project.service.LoginLogService;
import com.whkj.project.utils.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;


@RestController
public class LoginController {

    @Autowired
    LoginLogService loginLogService;

    @Autowired
    RedisTemplate redisTemplate;



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
     * 查询日志列表方法
     */

    @GetMapping(value = "/getLoginLog")
    public RestResult getLoginLog(){
        return RestResult.ok(loginLogService.getLoginLog());
    }








}
