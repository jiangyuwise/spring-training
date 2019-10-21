package com.codve.prospring.ch04;

import org.springframework.context.ApplicationEvent;

/**
 * 创建一个事件
 * 事件可以作为一种解耦方式, 如添加或修改文章信息, 发布一个事件
 * 缓存模块会接收事件, 并执行更新缓存的操作
 *
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
