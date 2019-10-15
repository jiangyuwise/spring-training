package com.codve.prospring.ch03.xml;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * 注入简单值, 使用 setter 和 Spring Express Language(SPEL)
 * 使用 app3.xml, 在 xml 中使用表达式
 * 值存储在 PersonConfig 中
 * @see PersonConfig
 */
public class Person3 {

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:xml/app3.xml");
        context.refresh();

        Person3 person3 = context.getBean("person3", Person3.class);
        System.out.println(person3.getName() + ": " + person3.getAge());

        context.close();
    }
}
