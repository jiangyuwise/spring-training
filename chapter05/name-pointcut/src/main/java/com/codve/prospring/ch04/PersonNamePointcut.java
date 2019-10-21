package com.codve.prospring.ch04;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;

/**
 * 使用名称匹配的方式创建切入点
 * 只匹配方法的名称, 不匹配参数和返回值.
 */
public class PersonNamePointcut {
    public static void main(String[] args) {
        Employee employee = new Employee();

        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.addMethodName("work");

        Advisor advisor = new DefaultPointcutAdvisor(pointcut, new PersonAdvice());
        // NameMatchMethodPointcutAdvisor 的便捷用法
//        Advisor advisor = new NameMatchMethodPointcutAdvisor(new PersonAdvice());
        ProxyFactory factory = new ProxyFactory();
        factory.addAdvisor(advisor);
        factory.setTarget(employee);

        Person proxy = (Person) factory.getProxy();

        // 有效切入点
        proxy.work();
        proxy.work(3);

        // 无效切入点
        proxy.rest();
    }
}
