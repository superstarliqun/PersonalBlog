package com.whkj.project.common.shiro;

import com.whkj.project.common.shiro.utils.CurrentCredentialsMatcher;
import com.whkj.project.common.shiro.utils.JwtToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * 4 自定义realm
 */
public class CustomerRealm extends AuthorizingRealm {


    // 设置realm的名称
    @Override
    public void setName(String name) {
        super.setName("customRealm");
    }

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("进来了授权");
        JwtToken jwtToken = (JwtToken) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermission("zdyl:test:1");
        return authorizationInfo;
    }

    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("进来了认证");
        JwtToken jwtToken = null;
        try {
            jwtToken = (JwtToken) authenticationToken;
        } catch (Exception e) {
            e.printStackTrace();
        }
        jwtToken.setUsername("1");
        String token = jwtToken.getToken();
        // 第二步：根据用户输入的userCode从数据库查询
        // ....
        // 如果查询不到返回null
        //数据库中用户账号是zhangsansan
        /*if(!userCode.equals("zhangsansan")){//
            return null;
        }*/
        // 模拟从数据库查询到密码
        String password = "111112";
        // 如果查询到返回认证信息AuthenticationInfo
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                jwtToken, password, this.getName());
        return simpleAuthenticationInfo;
    }

    /**
     * 自定义密码匹配器
     * @param credentialsMatcher
     */
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        //自定义密码匹配器
        CurrentCredentialsMatcher currentCredentialsMatcher = new CurrentCredentialsMatcher();

        super.setCredentialsMatcher(currentCredentialsMatcher);
    }
}