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
 * 配合使用切入点, 顾问, 通知
 * 创建静态切入点, 只有匹配成功的切入点才会生效
 * 静态切入点和动态切入点的差别在 MethodMatcher.isRuntime(), 返回 true 表示动态
 * 对于静态切入点, Spring 会对每一个方法调用MethodMatcher.matches(Method, Class<T>), 并缓存结果
 * 静态切入点性能较好
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
         * Employee 的切入点有效
         */
        ProxyFactory factory = new ProxyFactory();
        factory.addAdvisor(advisor); // 注意不是 addAdvice
        factory.setTarget(employee);
        proxy = (Person) factory.getProxy();
        proxy.work();


        /**
         * Student 的切入点无效
         */
        factory = new ProxyFactory();
        factory.addAdvisor(advisor);
        factory.setTarget(student);
        proxy = (Person) factory.getProxy();
        proxy.work();
    }
}
