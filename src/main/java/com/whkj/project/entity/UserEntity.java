package com.whkj.project.entity;


import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Data
@Repository
public class UserEntity implements UserDetails {

    private Integer id;

    private String userAccount;

    private String password;

    //第三方用户登录唯一标识
    private String openid;

    //登录用户类型
    private Integer loginStatus;

    //用户昵称
    private String nikeName;

    private Integer sex;

    //头像地址
    private String profile;

    private String uuid;

    private Set<String> powers;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
