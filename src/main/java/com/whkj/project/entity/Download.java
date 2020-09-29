package com.whkj.project.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class Download implements Serializable {
    private Integer id;

    /**
    * 源文件名称
    */
    private String originalName;

    /**
    * 文件路径
    */
    private String fileAddress;

    /**
    * 创建时间
    */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
    * 上传用户编号
    */
    private Integer userId;

    /**
    * 文件类型
    */
    private String fileType;

    /**
     * 文件传输数组
     */
    private List<MultipartFile> fileList;


    private static final long serialVersionUID = 1L;

}