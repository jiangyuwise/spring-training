package com.codve.prospring.ch04;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * 实现了 ApplicationContextAware 的 bean 可以获取到 context 的引用
 * 利用 context.registerShutdownHook() 可以完成 bean 销毁
 * setApplicationContext 由 Spring 调用.
 * 另见 app.xml 和 Writer.java
 */
public class ShutdownHock implements ApplicationContextAware {

    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (context instanceof GenericXmlApplicationContext) {
            ((GenericXmlApplicationContext) context).registerShutdownHook();
        }
    }
}
