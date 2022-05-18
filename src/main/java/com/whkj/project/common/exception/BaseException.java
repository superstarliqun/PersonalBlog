package com.whkj.project.common.exception;

/**
 * @author 衡钊清
 * @Classname BaseException
 * @Description 基础异常处理类
 * @Date 2020/2/12 15:43
 */
public class BaseException extends RuntimeException {

    /**
     * 异常编码
     */
    private String code;

    /**
     * 异常提示信息
     */
    private String msg;

    public BaseException() {
        super();
    }

    public BaseException(String code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
