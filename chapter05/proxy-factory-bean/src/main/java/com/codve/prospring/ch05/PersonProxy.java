package com.codve.prospring.ch05;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * 使用 XML 创建代理, 见 app-person.xml
 */
public class PersonProxy {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:app-person.xml");
        context.refresh();

        Person proxy = context.getBean("proxy", Person.class);
        proxy.work(); // 前置切片有效
        proxy.rest();
        context.close();
    }
}
