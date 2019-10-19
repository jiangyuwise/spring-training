package com.codve.prospring.ch04;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

/**
 * 创建名称匹配的切入点
 * 只匹配方法的名称, 不匹配参数和返回值.
 */
public class PersonNamePointcut {
    public static void main(String[] args) {
        Employee employee = new Employee();

        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.addMethodName("work");

        Advisor advisor = new DefaultPointcutAdvisor(pointcut, new PersonAdvice());
        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(employee);
        factory.addAdvisor(advisor);

        Person proxy = (Person) factory.getProxy();

        // 通知生效
        proxy.work();
        proxy.work(3);

        // 通知无效
        proxy.rest();
    }
}
