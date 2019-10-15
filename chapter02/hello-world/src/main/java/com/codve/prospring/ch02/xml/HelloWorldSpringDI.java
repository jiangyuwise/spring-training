package com.codve.prospring.ch02.xml;

import com.codve.prospring.ch02.decoupled.MessageRender;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 使用标准的 XML 注解 bean, 相应的配置文件为 /resources/xml/app.xml
 * 典型的依赖拉取
 */
public class HelloWorldSpringDI {
    public static void main(String[] args) {
        ApplicationContext context = new
                ClassPathXmlApplicationContext("xml/app.xml");
        MessageRender render = context.getBean("render", MessageRender.class);
        render.render();
    }
}
