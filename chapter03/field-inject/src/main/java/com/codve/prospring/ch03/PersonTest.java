package com.codve.prospring.ch03;

import org.springframework.context.support.GenericXmlApplicationContext;

public class PersonTest {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:field/app.xml");
        context.refresh();

        Person person = context.getBean("person", Person.class);
        person.info();
    }
}
