package com.jackting.common.bean.event.base;

/**
 * EventBus发送实体基类
 * @param <T>
 */
public class BaseEvent<T> {

    public int code;

    public String msg;

    public T data;

}
