package com.codve.prospring.example;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;

/**
 * 理解 AOP, 在 Person.work() 前后插入代码
 * PersonDecorator implements MethodInterceptor extends Person
 */
public class PersonDecorator implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("prepare");

        Object retVal = invocation.proceed();

        System.out.println("work done.");

        return retVal;
    }

    public static void main(String[] args) {
        Person person = new Person();

        ProxyFactory factory = new ProxyFactory();
        factory.addAdvice(new PersonDecorator());
        factory.setTarget(person);

        Person proxy = (Person) factory.getProxy();
        proxy.work();
    }
}
