package com.codve.prospring.ch04;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * 运行时, 传入不同的参数, 使用不同的配置文件
 * profile 配置文件中要加入 profile 属性
 * 在 vm options 中输入 -Dspring.profiles.active="employee"
 */
public class ProfileTest {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:student.xml"); // 加载所有的配置文件
        context.load("classpath:employee.xml"); // 加载所有的配置文件
        context.refresh();

        Person person = context.getBean("person", Person.class);
        person.info();

        context.close();
    }
}
