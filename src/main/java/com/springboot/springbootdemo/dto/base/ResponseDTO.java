package com.springboot.springbootdemo.dto.base;

import java.io.Serializable;

/**
 * description: ResponseDTO
 * date: 2019-08-23 14:34
 * author: Lee
 */
public class ResponseDTO<T> implements Serializable {
    private String code;
    private String msg;
    private T data;

    public ResponseDTO() {

    }

    public ResponseDTO(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseDTO(String code, String msg, T t) {
        this.code = code;
        this.msg = msg;
        this.data = t;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
