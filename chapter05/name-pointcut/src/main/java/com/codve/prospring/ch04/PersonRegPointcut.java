package com.codve.prospring.ch04;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;

/**
 * 创建正则表达式切入点
 * 只对方法名做匹配
 */
public class PersonRegPointcut {
    public static void main(String[] args) {
        Employee employee = new Employee();

        JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
        pointcut.setPattern(".*wo.*");
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, new PersonAdvice());

        ProxyFactory factory = new ProxyFactory();
        factory.addAdvisor(advisor);
        factory.setTarget(employee);

        Person proxy = (Person) factory.getProxy();

        // 生效的通知
        proxy.work();
        proxy.work(3);

        //无效的通知
        proxy.rest();
    }
}
