package com.whkj.project.controller;

import com.github.pagehelper.PageInfo;
import com.whkj.project.common.handler.exception.MyException;
import com.whkj.project.entity.Download;
import com.whkj.project.service.DownloadService;
import com.whkj.project.utils.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
public class DownloadController {

    @Autowired
    DownloadService downloadService;

    @Value("${pic.resource}")
    private String url;

    /**
     * 文件下载
     */
    @GetMapping(value = "/a")
    public ModelAndView download(PageInfo pageInfo){
        PageInfo list = downloadService.findAll(pageInfo);
        ModelAndView mav = new ModelAndView("/blog/blog_admin/download");
        mav.addObject("list", list);
        return mav;
    }

    /**
     * 上传文件
     * @param download
     * @return
     */
    @PostMapping(value = "/upload")
    public RestResult upload(Download download){
        downloadService.insertSelective(download);
        return RestResult.ok();
    }


    /**
     * 下载
     */
    @GetMapping(value = "/download/{id}")
    public void downloadList(HttpServletResponse response,@PathVariable("id") Integer id){
        Download download = downloadService.selectByPrimaryKey(id);
        download(response,url+download.getFileAddress().replace("/upload/",""));
    }


    /**
     * 下载
     * @param response
     * @param filePath
     * @return
     */
    public HttpServletResponse download(HttpServletResponse response, String filePath){
        response.setContentType("text/html;charset=utf-8");
        try {
            // path是指欲下载的文件的路径
            File file = new File(filePath);
            // 取得文件名
            String filename = file.getName();
            // 取得文件的后缀名
            // String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
            // 以流的形式下载文件
            InputStream fis = new BufferedInputStream(new FileInputStream(filePath));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes("utf-8"), "ISO8859-1"));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            throw new MyException("文件下载异常！");
        }
        return response;
    }

    }
