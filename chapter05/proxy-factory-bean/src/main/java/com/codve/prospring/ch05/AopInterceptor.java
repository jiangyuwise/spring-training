package com.codve.prospring.ch05;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * 在 app-aop-interceptor.xml 中 创建自定义的前置通知和环绕通知
 * @see PersonInterceptor
 */
public class AopInterceptor {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:app-aop-interceptor.xml");
        context.refresh();

        Person person = context.getBean("person", Person.class);
        // 检验带参数的前置通知
        person.work(3); // 有效
        person.work(8); // 无效

        // 检验环绕通知
         person.work(); // 有效

        Employee employee = context.getBean("employee", Employee.class);
        employee.work();


    }
}
