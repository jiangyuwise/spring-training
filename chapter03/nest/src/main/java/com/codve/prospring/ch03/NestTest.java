package com.codve.prospring.ch03;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * 嵌套 ApplicationContext
 */
public class NestTest {
    public static void main(String[] args) {
        GenericXmlApplicationContext parentContext = new GenericXmlApplicationContext();
        parentContext.load("classpath:parent.xml");
        parentContext.refresh();

        GenericXmlApplicationContext sonContext = new GenericXmlApplicationContext();
        sonContext.load("classpath:son.xml");
        sonContext.setParent(parentContext);
        sonContext.refresh();

        Son son = sonContext.getBean("son", Son.class);
        son.info();

        parentContext.close();
        sonContext.close();
    }
}
