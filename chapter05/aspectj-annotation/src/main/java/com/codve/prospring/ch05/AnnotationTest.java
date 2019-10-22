package com.codve.prospring.ch05;

import org.springframework.context.support.GenericXmlApplicationContext;

public class AnnotationTest {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:app.xml");
        context.refresh();

        Person person = context.getBean("person", Person.class);
        // 检查前置通知
        person.work(3);
        person.work(8);

        // 检查后置通知
        person.work();
        person.rest();

        Employee employee = context.getBean("employee", Employee.class);
        employee.work();

        context.close();
    }
}
