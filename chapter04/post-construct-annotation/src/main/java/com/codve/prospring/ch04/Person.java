package com.codve.prospring.ch04;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.support.GenericXmlApplicationContext;

import javax.annotation.PostConstruct;

/**
 * 使用 @PostConstruct 注解实现初始化后调用 bean 的指定方法
 * app.xml 中需要使用 <context:annotation-config/>
 */
public class Person {
    private String name;

    private int age;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @PostConstruct
    private void init() {
        if (name == null) {
            throw new IllegalArgumentException("No name set.");
        }
    }

    public void info() {
        System.out.println("name: " + name + ", age: " + age);
    }

    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:app.xml");
        context.refresh();

        Person person1 = context.getBean("person1", Person.class);
        person1.info();

        try {
            Person person2 = context.getBean("person2", Person.class);
            person2.info();
        } catch (BeanCreationException e) {
            System.out.println(e.getMessage());
        }
        context.close();
    }
}
