package com.codve.prospring.ch05;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * 在 app-person-advisor.xml 中创建 advisor
 * 其中, pointcut 是一个匿名 bean,  并且使用了 aspectj 表达式
 * 匿名 bean 可以防止被意外访问
 */
public class PersonAdvisor {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:app-person-advisor.xml");
        context.refresh();

        Person proxy = context.getBean("proxy", Person.class);
        proxy.work(); // 切片有效
        proxy.rest(); // 切片无效
    }
}
