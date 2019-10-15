package com.codve.prospring.ch03.xml;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * 注入 bean, 使用 xml/app1.xml, 利用了 setter
 * @see Apple
 * @see Fruit
 */
public class Person {
    private Fruit fruit;

    public void setFruit(Fruit fruit) {
        this.fruit = fruit;
    }

    public void info() {
        System.out.println("I have a " + fruit.getName());
    }

    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:xml/app1.xml");
        context.refresh();

        Person person = context.getBean("person", Person.class);
        person.info();

        context.close();
    }


}
