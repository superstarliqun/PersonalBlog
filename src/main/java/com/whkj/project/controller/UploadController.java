package com.whkj.project.controller;

import com.whkj.project.utils.EnumCode;
import com.whkj.project.utils.RestResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author SUPERSTARLIQUN
 */
@RestController
@RequestMapping("/resource")
public class UploadController {

    @Value(value = "${pic.resource}")
    private String path;

    @PostMapping(value = "/pic")
    public RestResult uploadPic(@RequestParam("file") MultipartFile file){
        StringBuilder original = new StringBuilder();
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            original.append(random.nextInt(10));
        }
        //生成文件名称通用方法
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Random r = new Random();
        StringBuilder tempName = new StringBuilder();
        tempName.append(original).append(sdf.format(new Date())).append(r.nextInt(100)).append(suffixName);
        String newFileName = tempName.toString();
        File fileDirectory = new File(path);
        //创建文件
        File destFile = new File(path+newFileName);
        try {
            if (!fileDirectory.exists()) {
                if (!fileDirectory.mkdir()) {
                    throw new IOException("文件夹创建失败,路径为：" + fileDirectory);
                }
            }
            file.transferTo(destFile);
            return RestResult.ok(newFileName);
        } catch (IOException e) {
            e.printStackTrace();
            return RestResult.build(EnumCode.BAD_REQUEST.getValue(),"文件上传失败！");
        }
    }
}
