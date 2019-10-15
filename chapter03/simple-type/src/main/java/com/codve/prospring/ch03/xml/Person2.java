package com.codve.prospring.ch03.xml;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Service;

/**
 * 注入简单值, 使用 app2.xml 文件和 java 注解, 不使用 setter 和 constructor
 * app2.xml 文件中不需要指定任何属性值
 */
@Service("person2")
public class Person2 {

    @Value("Jimmy")
    private String name;

    @Value("24")
    private int age;

    public void info() {
        System.out.println(name + ": " + age);
    }

    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:xml/app2.xml");
        context.refresh();

        Person2 person2 = context.getBean("person2", Person2.class);
        person2.info();

        context.close();
    }
}
