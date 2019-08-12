package com.jackting.common_http.result;

import com.google.gson.annotations.SerializedName;

/**
 * <p>Description:
 *
 * <p>Created by Devin Sun on 2017/3/25.
 */

public class HttpRespResult<T> {

    private static final int SUCCESS_CODE = 0;
    @SerializedName("errorMsg")
    private String msg;
    @SerializedName("errorCode")
    private Integer code;
    private T data;

    public boolean isSuccess() {
        return code != null && code == SUCCESS_CODE;
    }

    public String getMessage() {
        return msg;
    }

    public Integer getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "HttpResponseResult{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}
