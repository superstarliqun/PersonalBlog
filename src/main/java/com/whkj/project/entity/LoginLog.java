package com.whkj.project.entity;

import lombok.Data;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Date;

@Data
@Repository
public class LoginLog implements Serializable {
    private static final long serialVersionUID = -3382692890422085112L;

    private Integer id;

    /**
    * IP地址
    */
    private String ip;

    /**
    * 登录地址
    */
    private String address;

    /**
    * 创建时间
    */
    private Date createTime;
}