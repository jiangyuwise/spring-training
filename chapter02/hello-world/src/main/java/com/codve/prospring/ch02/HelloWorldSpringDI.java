package com.codve.prospring.ch02;

import com.codve.prospring.ch02.decoupled.MessageRender;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloWorldSpringDI {
    public static void main(String[] args) {
        ApplicationContext context = new
                ClassPathXmlApplicationContext("spring/application.xml");
        MessageRender render = context.getBean("render", MessageRender.class);
        render.render();
    }
}
