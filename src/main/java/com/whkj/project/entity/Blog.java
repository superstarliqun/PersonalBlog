package com.whkj.project.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
    * 博客表
    */
@Data
public class Blog implements Serializable {
    private Integer id;

    /**
    * 标题
    */
    private String title;

    /**
    * 内容
    */
    private String context;


    /**
     * 博文简介
     */
    private String briefIntroduction;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 创建用户编号
    */
    private Integer createUserId;

    /**
    * 修改用户时间
    */
    private Date updateTime;

    /**
    * 修改用户编号
    */
    private Integer updateUserId;

    /**
    * 观看人数
    */
    private String watch;

    /**
    * 1：正常   2：置顶
    */
    private Integer topping;

    /**
    * 分类编号
    */
    private Integer categoryId;

    /**
    * 点赞数量
    */
    private String fabulous;

    private static final long serialVersionUID = 1L;
}