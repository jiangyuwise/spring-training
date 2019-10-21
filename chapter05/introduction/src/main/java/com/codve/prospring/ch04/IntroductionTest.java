package com.codve.prospring.ch04;

import org.springframework.aop.IntroductionAdvisor;
import org.springframework.aop.framework.ProxyFactory;

public class IntroductionTest {
    public static void main(String[] args) {
        Person person = new Person();
//        person.setName("Jimmy");

        IntroductionAdvisor advisor = new IsModifiedAdvisor();
        ProxyFactory factory = new ProxyFactory();
        factory.addAdvisor(advisor);
        factory.setTarget(person);
        factory.setOptimize(true); // 使用 CGLIB 代理接口

        Person proxy = (Person) factory.getProxy();
        IsModified proxyInterface = (IsModified) proxy;

        System.out.println(proxyInterface.isModified()); // false

        proxy.setName("James");
        System.out.println(proxyInterface.isModified()); // true

        proxy.setName("James");
        System.out.println(proxyInterface.isModified()); // false
    }
}
