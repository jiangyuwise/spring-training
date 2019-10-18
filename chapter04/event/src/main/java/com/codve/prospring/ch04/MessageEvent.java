package com.codve.prospring.ch04;

import org.springframework.context.ApplicationEvent;

/**
 * 创建一个事件
 */
public class MessageEvent extends ApplicationEvent {
    private String msg;

    public MessageEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
