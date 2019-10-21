package com.codve.prospring.example;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

/**
 * 创建后置通知
 */
public class PersonAfter implements AfterReturningAdvice {
    /**
     * 后置通知可以抛出异常
     * @param returnValue 连接点方法的返回结果
     * @param method 连接点方法
     * @param args 连接点方法的参数
     * @param target 调用连接点方法的对象
     * @throws Throwable 异常
     */
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target)
            throws Throwable {
        System.out.println("work finished.");
    }

    public static void main(String[] args) {
        ProxyFactory factory = new ProxyFactory();
        factory.addAdvice(new PersonAfter());
        factory.setTarget(new Person());

        Person proxy = (Person) factory.getProxy();
        proxy.work();
    }
}
