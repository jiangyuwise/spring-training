package com.codve.prospring.ch04;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;

/**
 * 使用正则表达式创建切入点
 * 只对方法名做匹配
 */
public class PersonRegPointcut {
    public static void main(String[] args) {
        JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
        pointcut.setPattern(".*wo.*");
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, new PersonAdvice());

        ProxyFactory factory = new ProxyFactory();
        factory.addAdvisor(advisor);
        factory.setTarget(new Employee());

        Person proxy = (Person) factory.getProxy();

        // 有效切入点
        proxy.work();
        proxy.work(3);

        // 无效切入点
        proxy.rest();
    }
}
