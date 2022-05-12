package com.whkj.project.common.shiro;

import lombok.RequiredArgsConstructor;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Base64Utils;

import javax.servlet.Filter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedHashMap;

/**
 * @ClassName ShiroConfig.java
 * @Description  Shiro 配置类
 * @Author wuliqun
 * @Date 2022/5/12 11:14
 * @Version 1.0
 */
@Configuration
@RequiredArgsConstructor
public class ShiroConfig {

    //1创建shiroFilter  负责拦截请求
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置 securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        LinkedHashMap<String, Filter> myFilter = new LinkedHashMap<>();
        myFilter.put("auth", new AuthFilter());
        shiroFilterFactoryBean.setFilters(myFilter);

        LinkedHashMap<String, String> filterMap = new LinkedHashMap<>();
        // 设置免认证 url
        filterMap.put("/login", "anon");
        filterMap.put("/user/generateImages", "anon");
        filterMap.put("/getQQUrl", "anon");
        filterMap.put("/minio/*", "anon");
        filterMap.put("/**", "auth");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    //2.创建安全管理器
    @Bean(name="securityManager")
    public SecurityManager securityManager(MyRealm myRealm, SessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm);
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }

    // 3.创建自定义Realm
    @Bean(name="MyRealm")
    public MyRealm getRealm(){
        return new MyRealm();
    }


    //4.创建sessionManager
    @Bean("sessionManager")
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        //设置session过期时间3600s
        defaultWebSessionManager.setGlobalSessionTimeout(3600000L);
        return defaultWebSessionManager;
    }


    /**
     * Shiro生命周期处理器:
     * 用于在实现了Initializable接口的Shiro bean初始化时调用Initializable接口回调(例如:UserRealm)
     * 在实现了Destroyable接口的Shiro bean销毁时调用 Destroyable接口回调(例如:DefaultSecurityManager)
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 开启代码权限注释注解
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 实现spring的自动代理
     * @return
     */
    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator(){
        return new DefaultAdvisorAutoProxyCreator();
    }
}
