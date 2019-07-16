package com.lb.miaosha.commont.api;

public class CommonResult<T> {

    private int code;

    private String message;

    private T data;

    CommonResult(int code, String message, T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> CommonResult<T> success(T data){
        return new CommonResult<>(ResultCode.SUCCESS.getCode(),
                ResultCode.SUCCESS.getMessage(), data);
    }

    public static <T> CommonResult<T> success(String message){
        return new CommonResult<>(ResultCode.SUCCESS.getCode(),
                message, null);
    }

    public static <T> CommonResult<T> success(T data, String message){
        return new CommonResult<>(ResultCode.SUCCESS.getCode(),
                message, data);
    }

    public static <T> CommonResult<T> failed(IError iError){
        return new CommonResult<>(iError.getCode(),
                iError.getMessage(), null);
    }

    public static <T> CommonResult<T> failed(String message){
        return new CommonResult<>(ResultCode.FAILED.getCode(),
                message, null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
