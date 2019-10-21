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
 * 配合 advice 组成 advisor
 * 创建静态切入点, 只有匹配成功的切入点才会生效
 * 静态切入点和动态切入点的差别在 MethodMatcher.isRuntime(), 返回 true 表示动态
 * 对于静态切入点, Spring 会对每一个方法调用MethodMatcher.matches(Method, Class<T>), 并缓存结果
 * 静态切入点性能较好
 */
public class PersonStaticPointcut extends StaticMethodMatcherPointcut {

    // 匹配方法名称
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return method.getName().equals("work");
    }

    // 匹配类名称
    @Override
    public ClassFilter getClassFilter() {
        return cls -> (cls == Employee.class); // lambda 表达式
    }


    /**
     * Employee 和 Student 实现了相同的接口, 因此可以为接口创建代理, 而不是为具体的类创建代理.
     */
    public static void main(String[] args) {
        Pointcut pointcut = new PersonStaticPointcut();
        Advice advice = new PersonAdvice();
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, advice);
        // advisor 由 pointcut 和 advice 构成

        /**
         * Employee.work() 的切入点有效
         */
        ProxyFactory factory = new ProxyFactory();
        factory.addAdvisor(advisor); // 注意不是 addAdvice
        factory.setTarget(new Employee());
        Person proxy = (Person) factory.getProxy();
        proxy.work();


        /**
         * Student 的切入点无效
         */
        factory = new ProxyFactory();
        factory.addAdvisor(advisor);
        factory.setTarget(new Student());
        proxy = (Person) factory.getProxy();
        proxy.work();
    }
}
