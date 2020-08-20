package com.whkj.project.utils;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 自定义响应结构
 */
public class RestResult {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;

    private String flag;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public static RestResult build(Integer status, String msg, Object data) {
        return new RestResult(status, msg, data);
    }

    public static RestResult ok(Object data) {
        return new RestResult(data);
    }

    public static RestResult ok(String flag) {
        return new RestResult(flag);
    }

    public static RestResult ok() {
        return new RestResult(null);
    }

    public static RestResult resultOk(String flag, Object data){
        return new RestResult(flag, data);
    }
    public RestResult() {

    }

    public static RestResult build(Integer status, String msg) {
        return new RestResult(status, msg, null);
    }

    public RestResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
    public RestResult(Integer status, String msg, String flag) {
        this.status = status;
        this.msg = msg;
        this.flag = flag;
    }

    public RestResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    public RestResult(String flag) {
        this.status = 200;
        this.msg = "OK";
        this.flag = flag;
    }

    public RestResult(String flag, Object object) {
        this.status = 200;
        this.msg = "OK";
        this.flag = flag;
        this.data = object;
    }

//    public Boolean isOK() {
//        return this.status == 200;
//    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 将json结果集转化为RtrkmsResult对象
     *
     * @param jsonData json数据
     * @param clazz RtrkmsResult中的object类型
     * @return
     */
    public static RestResult formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, RestResult.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 没有object对象的转化
     *
     * @param json
     * @return
     */
    public static RestResult format(String json) {
        try {
            return MAPPER.readValue(json, RestResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object是集合转化
     *
     * @param jsonData json数据
     * @param clazz 集合中的类型
     * @return
     */
    public static RestResult formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

}
