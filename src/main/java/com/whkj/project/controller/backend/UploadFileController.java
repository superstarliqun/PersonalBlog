package com.whkj.project.controller.backend;

import com.whkj.project.utils.MinioUtilS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @ClassName UploadFileController.java
 * @Description  minio文件管理器
 * @Author wuliqun
 * @Date 2022/5/11 10:55
 * @Version 1.0
 */
@RestController
@RequestMapping("/minio")
public class UploadFileController {

    @Autowired
    private MinioUtilS minioUtilS;

    @Value("${minio.host}")
    private String address;

    @Value("${minio.bucket}")
    private String bucketName;

    /**
     * 上传文件
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("/upload")
    public Object upload(@RequestParam("file") MultipartFile file) throws Exception{
        List<String> upload = minioUtilS.upload(new MultipartFile[]{file});
        return address+"/"+bucketName+"/"+upload.get(0);
    }


    /**
     * 下载文件
     * @return
     */
    @GetMapping(value = "/download")
    public ResponseEntity<byte[]> download(){
        ResponseEntity<byte[]> download = minioUtilS.download("测试_1652246727233.pdf");
        return download;
    }

}
