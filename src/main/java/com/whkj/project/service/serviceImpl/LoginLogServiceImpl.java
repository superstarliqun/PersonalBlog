package com.whkj.project.service.serviceImpl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.whkj.project.mapper.LoginLogMapper;
import com.whkj.project.entity.LoginLog;
import com.whkj.project.service.LoginLogService;

import java.util.List;

@Service
public class LoginLogServiceImpl implements LoginLogService{

    @Resource
    private LoginLogMapper loginLogMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return loginLogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(LoginLog record) {
        return loginLogMapper.insert(record);
    }

    @Override
    public int insertSelective(LoginLog record) {
        return loginLogMapper.insertSelective(record);
    }

    @Override
    public LoginLog selectByPrimaryKey(Integer id) {
        return loginLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(LoginLog record) {
        return loginLogMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(LoginLog record) {
        return loginLogMapper.updateByPrimaryKey(record);
    }

    @Override
    @Cacheable(cacheNames = "login_log",key = "123")
    public List<LoginLog> getLoginLog() {
        return loginLogMapper.getLoginLog();
    }

}
