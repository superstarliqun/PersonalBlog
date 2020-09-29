package com.whkj.project.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whkj.project.common.handler.exception.MyException;
import com.whkj.project.utils.UploadUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.whkj.project.mapper.DownloadMapper;
import com.whkj.project.entity.Download;
import com.whkj.project.service.DownloadService;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
public class DownloadServiceImpl implements DownloadService{

    @Resource
    private DownloadMapper downloadMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return downloadMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(Download record) {
    try {
        for (MultipartFile m:record.getFileList()){
            record.setCreateTime(new Date());
            record.setFileAddress(UploadUtils.getImgUrl(m));
            record.setUserId(10086);
            record.setOriginalName(m.getOriginalFilename());
            downloadMapper.insertSelective(record);
        }
    }catch (Exception e){
        throw new MyException("文件数据保存异常！");
    }
        return 1;
    }

    @Override
    public Download selectByPrimaryKey(Integer id) {
        return downloadMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Download record) {
        return downloadMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public PageInfo findAll(PageInfo pageInfo) {
        PageHelper.startPage(pageInfo.getPageNum(),pageInfo.getPageSize());
        List<Download> list =  downloadMapper.findAll(pageInfo);
        return new PageInfo(list);
    }

}
