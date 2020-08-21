package com.whkj.project.common.handler.exception;

import org.apache.shiro.authc.IncorrectCredentialsException;

public class IncorrectException extends IncorrectCredentialsException {

    private static final long serialVersionUID = 2908828008749199746L;

    public IncorrectException(String errorMsg){
        super(errorMsg);
    }

}
