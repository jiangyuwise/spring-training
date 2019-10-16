package com.codve.prospring.ch03.annotation;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MethodInjectTest {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:annotation/app.xml");
        context.refresh();

        Person employee = context.getBean("employee", Employee.class);
        Person abstractEmployee = context.getBean("abstractEmployee", AbstractEmployee.class);
        System.out.println(employee.getAddress());
        System.out.println(abstractEmployee.getAddress());

        for (int i = 0; i < 5; i++) {
            System.out.println(employee.getAddress());
        }
        System.out.println();

        for (int i = 0; i < 5; i++) {
            System.out.println(abstractEmployee.getAddress());
        }

        context.close();
    }
}
