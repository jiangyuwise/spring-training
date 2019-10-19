package com.codve.prospring.ch04;

import org.springframework.aop.Advisor;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;

import java.lang.reflect.Method;

/**
 * 创建动态切入点
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
        Employee employee = new Employee();

        Advisor advisor = new DefaultPointcutAdvisor(
                new PersonDynamicPointcut(),
                new PersonAdvice()
        );

        ProxyFactory factory = new ProxyFactory();
        factory.addAdvisor(advisor);
        factory.setTarget(employee);

        Person proxy = (Person) factory.getProxy();

        // 参数小于 3, 通知不生效
        proxy.work(2);

        // 参数大于 3, 通知生效
        proxy.work(8);

    }
}
