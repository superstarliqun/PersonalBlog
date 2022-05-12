package com.whkj.project.controller.frontend;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Splitter;
import com.whkj.project.common.handler.exception.MyException;
import com.whkj.project.entity.UserEntity;
import com.whkj.project.service.UserService;
import com.whkj.project.utils.sendUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Random;

/**
 * 第三方登录接口控制器
 */
@Controller
public class PartyLoginController {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    UserService userService;

    /**
     * 第三方qq登录返回地址
     */
    @GetMapping(value = "/getQQUrl")
    @ResponseBody
    public String getQQUrl(HttpServletRequest request){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<6;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        String sessionId = request.getSession().getId();
        redisTemplate.opsForValue().set(sessionId,sb.toString());
        return "https://graph.qq.com/oauth2.0/authorize?response_type=code&client_id=101580413&redirect_uri=http%3A%2F%2Ftakeing.cn%3A8080%2FQQIntercept&state="+sb.toString();
    }



    @GetMapping(value = "/QQIntercept")
    public String QQIntercept(@RequestParam(value = "code",required = false) String code,
                                  @RequestParam(value = "state",required = false) String state,
                                  HttpServletRequest request){
        String nickname = null;
        try {
//            String data = (String) redisTemplate.opsForValue().get(requestedSessionId);
//            if(!StringUtils.equals(data,state)){
//                return RestResult.build(EnumCode.BAD_REQUEST.getValue(),EnumCode.ERROR_REQUEST.getText());
//            }
            String s = sendUtils.sendGet("https://graph.qq.com/oauth2.0/token", "grant_type=authorization_code&client_id=101580413&client_secret=24ef2b141a02578b7926ef37b765a9ea&redirect_uri=http%3A%2F%2Ftakeing.cn%3A8080%2FQQIntercept&code=" + code);
            Map<String, String> split = Splitter.on("&").withKeyValueSeparator("=").split(s);
            String access_token = split.get("access_token");
            String OpenID = sendUtils.sendGet("https://graph.qq.com/oauth2.0/me", "access_token=" + access_token);
            String replace = OpenID.replace("callback(", "").replace(");", "");
            JSONObject jsonObject = JSONObject.parseObject(replace);
            Object openid = jsonObject.get("openid");
            String s1 = sendUtils.sendGet("https://graph.qq.com/user/get_user_info", "access_token=" + access_token + "&oauth_consumer_key=101580413&openid=" + openid);
            JSONObject userInfo = JSONObject.parseObject(s1);

            //获取QQ资料信息   若QQ用户第一次授权登录，应让他先进行自己网站的账号注册，注册成功以后就可以直接通过QQ进行登录
            //openid值：此值与用户QQ号一一对应
            nickname = (String)userInfo.get("nickname");
            System.out.println(userInfo);
            //查询openid是否存在如果存在则不用添加用户
            Integer eixt = userService.findOpenIdExit((String)openid);
            if(eixt==0){
                UserEntity userEntity = new UserEntity();
                userEntity.setOpenid((String)openid);
                userEntity.setLoginStatus(2);
                userEntity.setSex((Integer) userInfo.get("gender_type"));
                userEntity.setNikeName((String) userInfo.get("nickname"));
                userEntity.setProfile((String) userInfo.get("figureurl_qq_2"));
                userService.createLoginUser(userEntity);
            }
        }catch (Exception e){
            throw new MyException("第三方工具发送或接收异常！");
        }
        //免密登录
        Subject subject = SecurityUtils.getSubject();
        return "redirect:/index";
    }


}
