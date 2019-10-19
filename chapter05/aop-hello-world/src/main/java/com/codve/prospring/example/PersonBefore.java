package com.codve.prospring.example;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import java.lang.reflect.Method;

/**
 * 创建前置通知
 */
public class PersonBefore implements MethodBeforeAdvice {
    /**
     * 利用前置通知可以拦截连接点方法的参数, 也可以抛出异常阻止连接点方法的调用.
     * @param method 连接点的方法(work)
     * @param args 连接点的方法所需的参数
     * @param target 调用方法的对象(person)
     * @throws Throwable
     */
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("prepare");
    }

    public static void main(String[] args) {
        Person person = new Person();

        ProxyFactory factory = new ProxyFactory();
        factory.addAdvice(new PersonBefore());
        factory.setTarget(person);

        Person proxy = (Person) factory.getProxy();
        proxy.work();
    }
}
