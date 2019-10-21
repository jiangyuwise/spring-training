package com.codve.prospring.example;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;

/**
 * 理解 AOP, 创建环绕通知
 * 环绕通知可以修改连接点传入的参数和返回的结果
 * 在 Person.work() 前后插入代码
 * PersonInterceptor implements MethodInterceptor extends Person
 */
public class PersonInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("prepare");

        Object obj = invocation.proceed();

        System.out.println("work done.");

        return obj;
    }

    public static void main(String[] args) {
        Person person = new Person();

        ProxyFactory factory = new ProxyFactory();
        factory.addAdvice(new PersonInterceptor());
        factory.setTarget(person);

        Person proxy = (Person) factory.getProxy();
        proxy.work();
    }
}
