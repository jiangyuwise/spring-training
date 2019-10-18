package com.codve.prospring.ch04;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 事件的发布者和监听器需要在 app.xml 中注册
 * Publisher 发布消息时, 将 事件 MessageEvent 发送到 ApplicationContext
 * Publisher 通过实现 ApplicationContextAware 来访问 ApplicationContext
 */
public class Publisher implements ApplicationContextAware {
    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.context = applicationContext;
    }

    // 发布消息
    public void publish(String msg) {
        context.publishEvent(new MessageEvent(this, msg));
    }

    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:app.xml");
        Publisher publisher = context.getBean("publisher", Publisher.class);
        publisher.publish("SOS");
        publisher.publish("EMG");
    }
}
