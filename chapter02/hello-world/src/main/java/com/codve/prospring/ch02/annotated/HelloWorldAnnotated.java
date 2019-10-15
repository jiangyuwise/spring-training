package com.codve.prospring.ch02.annotated;

import com.codve.prospring.ch02.decoupled.MessageRender;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HelloWorldAnnotated {
    public static void main(String[] args) {
        ApplicationContext context = new
                AnnotationConfigApplicationContext(HelloWorldConfig.class);
        MessageRender render = context.getBean("render", MessageRender.class);
        render.render();
        
    }
}
