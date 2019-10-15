package com.codve.prospring.ch03.xml;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * 注入简单值, app.xml 文件需要指定属性值
 */
public class Person {
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
        context.load("classpath:xml/app.xml");
        context.refresh();

        Person person = context.getBean("person", Person.class);
        System.out.println(person.getName() + ": " + person.getAge());

        context.close();
    }
}
