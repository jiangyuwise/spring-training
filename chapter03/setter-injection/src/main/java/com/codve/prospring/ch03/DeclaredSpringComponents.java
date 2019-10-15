package com.codve.prospring.ch03;

import com.codve.prospring.ch02.decoupled.MessageRender;
import org.springframework.context.support.GenericXmlApplicationContext;

public class DeclaredSpringComponents {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:annotated/app.xml");
        context.refresh();

        MessageRender render = context.getBean("render", MessageRender.class);
        render.render();

        context.close();
    }
}
