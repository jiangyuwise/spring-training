package com.codve.prospring.ch05;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:app.xml");
        context.refresh();

        Person person = new Person();
        person.work(3);
        person.work(8);

        context.close();
    }
}
