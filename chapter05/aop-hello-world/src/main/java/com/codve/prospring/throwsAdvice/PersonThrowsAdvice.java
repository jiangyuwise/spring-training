package com.codve.prospring.throwsAdvice;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

/**
 * 异常通知, 只有在产生异常时才起作用
 * 只能修改返回的异常类型
 */
public class PersonThrowsAdvice implements ThrowsAdvice {
    public void afterThrowing(Exception e) {
        System.out.println("Generic Exception captured.");
    }

    public void afterThrowing(Method method, Object args, Object target,
                              IllegalArgumentException e) {
        System.out.println("IllegalArgument Exception captured.");
        System.out.println(method.getName());
    }

    public static void main(String[] args) {

        Person person = new Person();

        ProxyFactory factory = new ProxyFactory();
        factory.addAdvice(new PersonThrowsAdvice());
        factory.setTarget(person);

        Person proxy = (Person) factory.getProxy();

        try {
            proxy.workException();
        } catch (Exception e) {

        }

        try {
            proxy.workIllegalException();
        } catch (IllegalArgumentException e) {

        }

    }
}
