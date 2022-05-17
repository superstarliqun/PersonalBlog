package com.whkj.project.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("login_log")
public class LoginLog implements Serializable {

    private static final long serialVersionUID = -3382692890422085112L;

    @TableId
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