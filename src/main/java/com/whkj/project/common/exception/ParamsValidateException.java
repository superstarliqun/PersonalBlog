package com.whkj.project.common.exception;



/**
 * @author 衡钊清
 * @Classname ParamsValidateException
 * @Description 参数校验异常
 * @Date 2020/2/12 16:54
 */
public class ParamsValidateException extends BaseException {

    public ParamsValidateException() {
        super();
    }

    public ParamsValidateException(String code, String msg) {
        super(code, msg);
    }
}
