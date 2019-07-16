package com.lb.miaosha.commont.api;

import com.lb.miaosha.commont.api.IError;

public enum ResultCode implements IError {
    SUCCESS(200, "请求成功"),
    FAILED(404, "请求参数异常");

    private int code;

    private String message;

    ResultCode(int code, String message){
        this.code = code;
        this.message = message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int getCode() {
        return 0;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
