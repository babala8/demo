package com.example.demo.skill.apimethod.exception.globalexceptionhandler;

/**
 * @author ldy
 * @version 1.0
 */
public class RespBean<T> {

    private long code;
    private String message;
    private T data;

    public static <Type> RespBean<Type> successWithoutData() {
        return new RespBean<Type>(RespBeanEnum.SUCCESS.getCode(), RespBeanEnum.SUCCESS.getMessage(), null);
    }

    public static <Type> RespBean<Type> successWithData(Type data) {
        return new RespBean<Type>(RespBeanEnum.SUCCESS.getCode(), RespBeanEnum.SUCCESS.getMessage(), data);
    }

    public static <Type> RespBean<Type> error(RespBeanEnum respBeanEnum) {
        return new RespBean<Type>(respBeanEnum.getCode(), respBeanEnum.getMessage(), null);
    }

    public static <Type> RespBean<Type> error(RespBeanEnum respBeanEnum, Type data) {
        return new RespBean<Type>(respBeanEnum.getCode(), respBeanEnum.getMessage(), data);
    }

    public RespBean(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public RespBean(){

    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
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
