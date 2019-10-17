package com.codve.prospring.ch04;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * 所有实现 BeanNameAware 的 bean, 可以拿到它在容器中的名称
 */
public class Person implements BeanNameAware {
    private String beanName;

    // Spring 容器负责调用 setBeanName, 并将 bean 的id传进来.
    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public void info() {
        System.out.println("bean name: " + beanName);
    }

    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:app.xml");
        context.refresh();

        Person person = context.getBean("person", Person.class);
        person.info();

        context.close();
    }
}
