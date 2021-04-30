package com.maeng.shop.common;

public class CommonResponse<T> {
    private String returnCode;
    private String returnMessage;
    private T data;

    public String getReturnCode() {
        return returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public T getData() {
        return data;
    }
}
