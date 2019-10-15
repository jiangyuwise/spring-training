package com.codve.prospring.ch03.annotation;

import com.codve.prospring.ch02.decoupled.MessageProvider;
import org.springframework.context.support.GenericXmlApplicationContext;

public class AnnotationMessageProviderTest {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:annotation/app.xml");
        context.refresh();

        MessageProvider provider = context.getBean("provider", AnnotationMessageProvider.class);
        System.out.println(provider.getMessage());
        context.close();
    }
}
