package com.codve.prospring.ch03.xml;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Service;

/**
 * 简单值注入, 使用外部配置文件 PersonConfig, java 注解, SPEL 表达式
 * 使用 app2.xml
 */
@Service("person4")
public class Person4 {

    @Value("#{personConfig.name}")
    private String name;

    @Value("#{personConfig.age}")
    private int age;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:xml/app2.xml");
        context.refresh();

        Person4 person4 = context.getBean("person4", Person4.class);
        System.out.println(person4.getName() + ": " + person4.getAge());

        context.close();
    }
}
