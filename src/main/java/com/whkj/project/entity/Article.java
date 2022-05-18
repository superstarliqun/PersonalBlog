package com.whkj.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("blog_article")
public class Article implements Serializable {

    private Integer id;

    private String title;

    private LocalDateTime createTime;

    private String content;

    private Integer sort;

    private String createUser;

    private String author;

    private Integer type;
}
