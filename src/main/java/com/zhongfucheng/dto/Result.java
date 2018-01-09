package com.zhongfucheng.dto;

/**
 * Created by ozc on 2018/1/4.
 *
 * @author ozc
 * @version 1.0
 * <p>
 * 返回给页面的对象（封装该对象为了统一返回给页面的JSON类型）
 */
public class Result<T> {

    /** 错误码. */
    private Integer code;

    /** 提示信息. */
    private String msg;

    /** 具体的内容. */
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
