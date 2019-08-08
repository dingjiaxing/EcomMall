package com.lib.http.result;

/**
 * <p>Description:
 * <p>
 * <p>Created by Devin Sun on 2017/3/25.
 */

public class HttpRespException extends RuntimeException {

    private int status;
    private int code;

    public HttpRespException(String message, int status, int code) {
        super(message);
        this.status = status;
        this.code=code;
    }

    public int getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }
}
