package com.codve.prospring.ch04;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;

import java.lang.reflect.Method;

/**
 * 创建动态切入点
 * 动态切入点比静态切入点多一个匹配参数的步骤
 */
public class PersonDynamicPointcut extends DynamicMethodMatcherPointcut {

    // 匹配参数
    @Override
    public boolean matches(Method method, Class<?> targetClass, Object... args) {
        int arg = ((Integer) args[0]);
        return arg > 3;
    }
    // 匹配方法名称
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return "work".equals(method.getName());
    }

    // 匹配类名称
    @Override
    public ClassFilter getClassFilter() {
        return cls -> (cls == Employee.class); // lambda 表达式
    }

    public static void main(String[] args) {
        Pointcut pointcut = new PersonDynamicPointcut();
        Advice advice = new PersonAdvice();
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, advice);

        ProxyFactory factory = new ProxyFactory();
        factory.addAdvisor(advisor);
        factory.setTarget(new Employee());

        Person proxy = (Person) factory.getProxy();

        // 参数小于 3, 无效切入点
        proxy.work(2);

        // 参数大于 3, 有效切入点
        proxy.work(8);

    }
}
