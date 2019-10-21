package com.codve.prospring.ch05;

import org.springframework.aop.Advisor;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcher;

import java.lang.reflect.Method;

/**
 * 创建组合切入点
 * union 表示并
 * intersection 表示交
 */
public class ComposablePointcutTest {

    public Person getProxy(ComposablePointcut pointcut, Person person) {
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, new PersonBeforeAdvice());
        ProxyFactory factory = new ProxyFactory();
        factory.addAdvisor(advisor);
        factory.setTarget(person);

        return (Person) factory.getProxy();
    }

    private static class workMethodMatcher extends StaticMethodMatcher {
        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            return method.getName().equals("work");
        }
    }

    private static class restMethodMatcher extends StaticMethodMatcher {
        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            return method.getName().equals("rest");
        }
    }


    public static void main(String[] args) {
        ComposablePointcutTest test = new ComposablePointcutTest();
        Person person = new Employee();
        ComposablePointcut pointcut = new ComposablePointcut(ClassFilter.TRUE,
                new workMethodMatcher());
        Person proxy = test.getProxy(pointcut, person);

        proxy.work();
        proxy.rest(); // 切入点无效
        System.out.println();

        // 两个方法的并集, 切入点都有效
        pointcut.union(new restMethodMatcher());
        proxy = test.getProxy(pointcut, person);
        proxy.work();
        proxy.rest();
        System.out.println();

        // 再与 rest 方法求交集, 只剩下 rest 方法.
        pointcut.intersection(new restMethodMatcher());
        proxy = test.getProxy(pointcut, person);
        proxy.work(); // 切入点无效
        proxy.rest();
        System.out.println();
    }
}
