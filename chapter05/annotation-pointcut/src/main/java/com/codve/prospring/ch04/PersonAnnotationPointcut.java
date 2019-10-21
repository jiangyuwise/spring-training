package com.codve.prospring.ch04;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;

/**
 * 使用自定义注解创建切入点
 * @see AdviceAnnotation
 * @see Employee
 */
public class PersonAnnotationPointcut {
    public static void main(String[] args) {
        AnnotationMatchingPointcut pointcut = AnnotationMatchingPointcut
                .forMethodAnnotation(AdviceAnnotation.class);
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, new PersonAdvice());

        ProxyFactory factory = new ProxyFactory();
        factory.addAdvisor(advisor);
        factory.setTarget(new Employee());

        Employee proxy = (Employee) factory.getProxy();

        // 有效的切入点
        proxy.work(3);

        // 无效的切入点
        proxy.work();
        proxy.rest();
    }
}
