package com.codve.prospring.ch03.annotated;

import com.codve.prospring.ch02.decoupled.MessageProvider;
import com.codve.prospring.ch02.decoupled.MessageRender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 使用注解创建 bean, name = "render"
 * 配合 HelloWorldMessageProvider, DeclaredSpringComponents, annotated/app.xml 食用
 * @see com.codve.prospring.ch03.DeclaredSpringComponents
 */
@Service("render")
public class StandardMessageRender implements MessageRender {
    private MessageProvider messageProvider;

    @Override
    public void render() {
        if (messageProvider == null) {
            throw new RuntimeException("messageProvider must be set.");
        }
        System.out.println(messageProvider.getMessage());
    }

    /**
     * Spring IoC 容器会查找 MessageProvider 类型的 bean, 作为参数传入
     * 效果与 p:messageProvider-ref="provider" 一致
     * 方法里的 this 一定不能少, 否则就会注入失败.
     */
    @Override
    @Autowired
    public void setMessageProvider(MessageProvider provider) {
        this.messageProvider = provider;
    }

    @Override
    public MessageProvider getMessageProvider() {
        return this.messageProvider;
    }
}
