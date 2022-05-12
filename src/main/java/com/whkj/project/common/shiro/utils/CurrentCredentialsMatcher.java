package com.whkj.project.common.shiro.utils;

import com.whkj.project.common.shiro.utils.JwtToken;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * @ClassName CurrentCredentialsMatcher.java
 * @Description  自定义密码匹配器
 * @Author wuliqun
 * @Date 2022/5/12 11:14
 * @Version 1.0
 */
public class CurrentCredentialsMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        JwtToken jwtToken = (JwtToken) token;
        Object tokenCredentials = jwtToken.getToken();
        Object accountCredentials = this.getCredentials(info);
        return this.equals(tokenCredentials, accountCredentials);
    }
}
