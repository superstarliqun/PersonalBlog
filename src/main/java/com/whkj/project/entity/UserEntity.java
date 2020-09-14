package com.whkj.project.entity;


import lombok.Data;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Data
@Repository
public class UserEntity implements Serializable {

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


}
