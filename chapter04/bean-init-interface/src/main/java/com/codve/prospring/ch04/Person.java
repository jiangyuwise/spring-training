package com.codve.prospring.ch04;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * 使用继承接口的方式, 让 bean 初始化后执行指定的方法
 * app.xml 就无需指定 init-method
 * 另见 bean-init-method/
 */
public class Person implements InitializingBean {
    private String name;
    private int age;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // 接口声明的方法, 表示在 bean 完成配置后执行
    // 完成配置指的是, 如果在 xml 中配置了 p:name="Jimmy", p:age="24",
    // spring 就会调用 bean 的 setName(), setAge(). 这个过程就称为配置.
    @Override
    public void afterPropertiesSet() throws Exception {
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
