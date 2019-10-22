package com.codve.prospring.ch05;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * 使用 XML 创建代理, 见 person-before-advice.xml
 */
public class PersonBeforeAdviceTest {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:person-before-advice.xml");
        context.refresh();

        Person proxy = context.getBean("proxy", Person.class);
        proxy.work();
        proxy.rest();
        context.close();
    }
}
