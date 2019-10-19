package com.codve.prospring.ch04;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

/**
 * 创建静态切入点, 只有匹配成功的切入点才会生效
 */
public class PersonStaticPointcut extends StaticMethodMatcherPointcut {

    // 匹配方法名称
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return ("work".equals(method.getName()));
    }

    // 匹配类名称
    @Override
    public ClassFilter getClassFilter() {
        return cls -> (cls == Employee.class); // lambda 表达式
    }

    public static void main(String[] args) {
        Employee employee = new Employee();
        Student student = new Student();
        Person proxy;

        Pointcut pointcut = new PersonStaticPointcut();
        Advice advice = new PersonAdvice();
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, advice);

        /**
         * Employee 的环绕通知有效
         */
        ProxyFactory factory = new ProxyFactory();
        factory.addAdvisor(advisor); // 注意不是 addAdvice
        factory.setTarget(employee);
        proxy = (Person) factory.getProxy();
        proxy.work();


        /**
         * Student 的环绕通知无效
         */
        factory = new ProxyFactory();
        factory.addAdvisor(advisor);
        factory.setTarget(student);
        proxy = (Person) factory.getProxy();
        proxy.work();
    }
}
