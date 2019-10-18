package com.codve.prospring.ch04;

import org.springframework.context.ApplicationListener;

/**
 * 定义监听器, 定义收到事件后的动作
 */
public class MessageEventListener implements ApplicationListener<MessageEvent> {
    @Override
    public void onApplicationEvent(MessageEvent event) {
        System.out.println(event.getMsg());
    }
}
