package com.codve.prospring.ch04;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * 在 XML 中指定 bean 在完成初始化后应该执行的方法:
 * 可以在 beans 结点上指定 default-init-method = "init",
 * 也可以在 bean 结点上指定 init-method="init"
 * spring 默认在启动 context 时初始化 bean, default-lazy-init 为 true 时表示只有在 getBean 时才创建.
 * 见 app.xml
 *
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

    // init 抛出的异常会被 spring 封装为 BeanCreationException
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

        try {
            Person person1 = context.getBean("person1", Person.class);
            person1.info();
        } catch (BeanCreationException e) {
            System.out.println(e.getMessage());
        }

        try {
            Person person2 = context.getBean("person2", Person.class);
            person2.info();
        } catch (BeanCreationException e) {
            System.out.println(e.getMessage());
        }
        context.close();
    }
}
