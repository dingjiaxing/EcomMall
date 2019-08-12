package com.jackting.common_http;

/**
 * <p>Description:
 * <p>Company：
 * <p>Email:
 * <p>Created by Devin Sun on 2017/10/1.
 */

public class TokenExpiredEvent {
    private String message;

    public TokenExpiredEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
