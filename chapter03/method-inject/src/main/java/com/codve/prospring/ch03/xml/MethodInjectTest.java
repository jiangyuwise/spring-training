package com.codve.prospring.ch03.xml;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * 查找方法注入
 */
public class MethodInjectTest {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:xml/app.xml");
        context.refresh();

        Person employee = context.getBean("employee", Employee.class);
        Person abstractEmployee = context.getBean("abstractEmployee", AbstractEmployee.class);

        Address address1 = employee.getAddress();
        Address address2 = abstractEmployee.getAddress();

        System.out.println("address1: " + address1);
        System.out.println("address2: " + address2);
        System.out.println();

        // address1 每次都是一样的
        for (int i = 0; i < 5; i++) {
            address1 = employee.getAddress();
            System.out.println("address1: " + address1);
        }
        System.out.println();

        // address2 每次都是新的
        for (int i = 0; i < 5; i++) {
            address2 = abstractEmployee.getAddress();
            System.out.println("address2: " + address2);
        }

        context.close();
    }
}
