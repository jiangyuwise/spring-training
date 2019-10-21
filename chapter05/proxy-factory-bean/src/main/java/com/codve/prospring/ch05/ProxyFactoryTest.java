package com.codve.prospring.ch05;

import org.springframework.context.support.GenericXmlApplicationContext;

public class ProxyFactoryTest {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:app.xml");
        context.refresh();

        Employee employee1 = context.getBean("employee1", Employee.class);
        employee1.work(); // 此时 person 的 2 个方法都有切入点

        Employee employee2 = context.getBean("employee2", Employee.class);
        employee2.work(); // 此时只有 person.work() 方法有切入点

        context.close();
    }
}
