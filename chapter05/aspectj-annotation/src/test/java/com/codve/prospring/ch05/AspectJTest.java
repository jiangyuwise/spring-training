package com.codve.prospring.ch05;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.junit.Assert.*;

public class AspectJTest {

    @Test
    public void xmlTest() {
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

    @Test
    public void configTest() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AnnotationConfigTest.class);

        Person person = context.getBean("person", Person.class);
        person.work(3);
        person.work(8);
        context.close();
    }
}