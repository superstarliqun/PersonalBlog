package com.whkj.project.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.whkj.project.utils.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.whkj.project.mapper.LoginLogMapper;
import com.whkj.project.entity.LoginLog;
import com.whkj.project.service.LoginLogService;

import java.util.List;

@Service
public class LoginLogServiceImpl implements LoginLogService{

    @Autowired(required = false)
    private LoginLogMapper loginLogMapper;

    @Override
    public RestResult queryLoginLog() {
        LambdaQueryWrapper<LoginLog>  lambdaQueryWrapper = new LambdaQueryWrapper();
        List<LoginLog> loginLogs = loginLogMapper.selectList(lambdaQueryWrapper);
        return RestResult.ok(loginLogs);
    }
}
