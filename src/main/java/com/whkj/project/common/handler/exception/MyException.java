package com.whkj.project.common.handler.exception;

import org.apache.shiro.authc.UnknownAccountException;

/**
 * 学生组织OA系统内部异常类
 */
public class MyException extends UnknownAccountException {

    private static final long serialVersionUID = 2908828008749199746L;

    public MyException(String errorMsg){
        super(errorMsg);
    }
}
