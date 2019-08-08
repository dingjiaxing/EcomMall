package com.lib.http.result;

/**
 * <p>Description:
 *
 * <p>Created by Devin Sun on 2017/3/25.
 */

public class HttpRespResult<T> {

    private static final int SUCCESS_CODE = 0;

    private String errorMsg;
    private Integer errorCode;
    private T data;

    public boolean isSuccess() {
        return errorCode != null && errorCode == SUCCESS_CODE;
    }

    public String getMessage() {
        return errorMsg;
    }

    public Integer getCode() {
        return errorCode;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "HttpResponseResult{" +
                "msg='" + errorMsg + '\'' +
                ", errorCode=" + errorCode +
                ", data=" + data +
                '}';
    }
}
