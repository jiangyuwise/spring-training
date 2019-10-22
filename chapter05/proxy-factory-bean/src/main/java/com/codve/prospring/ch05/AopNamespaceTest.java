package com.codve.prospring.ch05;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * 在 app-aop.xml 中使用 aop 命名空间
 * 在调用 Person.work() 时, 调用会被拦截
 */
public class AopNamespaceTest {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:app-aop.xml");
        context.refresh();

        Person person = context.getBean("person", Person.class);
        person.work(); // 切入点有效
        person.rest(); // 切入点无效

        Employee employee = context.getBean("employee", Employee.class);
        employee.work();

        context.close();
    }
}
