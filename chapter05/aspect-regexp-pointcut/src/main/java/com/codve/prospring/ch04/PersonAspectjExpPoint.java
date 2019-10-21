package com.codve.prospring.ch04;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * 使用 Aspectj 正则表达式创建切入点
 */
public class PersonAspectjExpPoint {
    public static void main(String[] args) {
        Employee employee = new Employee();

        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* wo*(..))");
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, new PersonAdvice());

        ProxyFactory factory = new ProxyFactory();
        factory.addAdvisor(advisor);
        factory.setTarget(employee);

        Person proxy = (Person) factory.getProxy();

        // 有效的切入点
        proxy.work();
        proxy.work(3);

        // 无效的切入点
        proxy.rest();
    }
}
