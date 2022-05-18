package com.whkj.project.common.configure;

import com.whkj.project.entity.UserEntity;
import com.whkj.project.utils.AuthenticationUtil;
import com.whkj.project.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Component
public class UserRequestUtil {

    private static RedisUtil redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @PostConstruct
    public void init() {
        redisTemplate = redisUtil;
    }

    public static UserEntity getLoginUser() {
        UserEntity user = null;
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String tokenHeader = request.getHeader(AuthenticationUtil.getTokenHeader());
            if (null != tokenHeader && !tokenHeader.equals("")) {
                String usernameMd5 = AuthenticationUtil.getTokenAESde(tokenHeader)[1];
                Object o = redisTemplate.get(usernameMd5);
                if (null != o) user = (UserEntity) redisTemplate.get(usernameMd5);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return user;
    }

    public static String getTicket() {
        String ticket = "";
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String tokenHeader = request.getHeader(AuthenticationUtil.getTokenHeader());
            if (null != tokenHeader && !tokenHeader.equals("")) {
                ticket = AuthenticationUtil.getTokenAESde(tokenHeader)[1];
            }
        } catch (Exception e) {
        }
        return ticket;
    }

    public static void removeToken(List<String> list) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Object[] objects = list.toArray();
                    String[] strings = new String[objects.length];
                    for (int i = 0; i < objects.length; i++) {
                        strings[i] = AuthenticationUtil.getTokenCode((String) objects[i]);
                    }
                    redisTemplate.del(strings);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public static void removeToken(String username) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    redisTemplate.del(AuthenticationUtil.getTokenCode(username));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

}
