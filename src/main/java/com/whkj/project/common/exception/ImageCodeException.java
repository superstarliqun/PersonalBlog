package com.whkj.project.common.exception;

import lombok.Data;

import java.io.Serializable;
/**
 *
 * Description:图片验证码相关异常
 * @author huangweicheng
 * @date 2019/10/22
 */
@Data
public class ImageCodeException extends RuntimeException implements Serializable
{
    private static final long serialVersionUID = 4554L;

    private String code;

    public ImageCodeException()
    {
    }

    public ImageCodeException(String message)
    {
        super(message);
    }

    public ImageCodeException(String code,String message)
    {
        super(message);
        this.code = code;
    }

    public ImageCodeException(String message,Throwable cause)
    {
        super(message,cause);
    }

    public ImageCodeException(Throwable cause)
    {
        super(cause);
    }

    public ImageCodeException(String message,Throwable cause,boolean enableSupperssion,boolean writablesStackTrace)
    {
        super(message,cause,enableSupperssion,writablesStackTrace);
    }

}