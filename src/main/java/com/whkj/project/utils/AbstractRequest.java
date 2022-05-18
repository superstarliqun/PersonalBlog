package com.whkj.project.utils;

import java.io.Serializable;

/**
 * @Classname AbstractRequest
 * @Description 抽象请求参数
 * @Date 2020/2/12 08:53
 * @author 衡钊清
 */
public abstract class AbstractRequest implements Serializable {

    private static final long serialVersionUID = -9163815538622000225L;

    /**
     * @Author 衡钊清
     * @Description 请求参数检查
     * @Date 2020/2/12 10:45
     **/
    public abstract void checkParams();

}
