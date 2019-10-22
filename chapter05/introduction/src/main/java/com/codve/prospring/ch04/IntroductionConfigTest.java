package com.codve.prospring.ch04;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * 使用 XML 文件创建 introduction
 */
public class IntroductionConfigTest {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:app.xml");
        context.refresh();

        Person proxy = context.getBean("proxy", Person.class);
        IsModified isModified = (IsModified) proxy;

        proxy.setName("Jimmy");
        System.out.println(isModified.isModified()); // false

        proxy.setName("James");
        System.out.println(isModified.isModified()); // true

        proxy.setName("James");
        System.out.println(isModified.isModified()); // false

        context.close();
    }
}
