package com.codve.prospring.ch05;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 *各种代理的性能测试
 * JDK 只能为接口生成代理, CGLIB 可以代理接口和类
 * CGLIB 以字节码的形式生成生成代理的子类
 * CGLIB 和 JDK 性能差不多, 但 CGLIB 固定链性能较好
 **/
public class ProxyPerform {

    // 测试 CGLIB
    public void cglib(Advisor advisor, Person person) {
        ProxyFactory factory = new ProxyFactory();
        factory.setProxyTargetClass(true);
        factory.addAdvisor(advisor);
        factory.setTarget(person);

        Person proxy = (Person) factory.getProxy();
        System.out.println("CGLIB performance:");
        test(proxy);
    }

    // 测试 CGLIB 固定通知链
    public void cglibFrozen(Advisor advisor, Person person) {
        ProxyFactory factory = new ProxyFactory();
        factory.setProxyTargetClass(true); // todo
        factory.addAdvisor(advisor);
        factory.setTarget(person);
        factory.setFrozen(true); // 固定通知链

        Person proxy = (Person) factory.getProxy();
        System.out.println("CGLIB Frozen performance");
        test(proxy);
    }

    // 测试JDK 代理
    public void jdk(Advisor advisor, Person person) {
        ProxyFactory factory = new ProxyFactory();
        factory.addAdvisor(advisor);
        factory.setTarget(person);
        factory.setInterfaces(Person.class); // 指定 JDK 代理

        Person proxy = (Person) factory.getProxy();
        System.out.println("JDK performance: ");
        test(proxy);
    }
    private void test(Person proxy) {
        long start = 0;
        long end = 0;
        long cost = 0;
        // 测试有切入点的方法
        System.out.println("test work() method:");
        start = System.nanoTime(); // 系统当前的纳秒
        for (int i = 0; i < 500000; i++) {
            proxy.work();
        }
        end = System.nanoTime();
        cost = (end - start) / 1000000;
        System.out.println("took " +  cost + " ms\n");

        // 测试没有切入点的方法
        System.out.println("test rest() method:");
        start = System.nanoTime();
        for (int i = 0; i < 500000; i++) {
            proxy.rest();
        }
        end = System.nanoTime();
        cost = (end - start) / 1000000;
        System.out.println("took " + cost + " ms\n");

        // 测试 equal
        System.out.println("test equals() method:");
        start = System.nanoTime();
        for (int i = 0; i < 500000; i++) {
            proxy.rest();
        }
        end = System.nanoTime();
        cost = (end - start) / 1000000;
        System.out.println("took " + cost + " ms\n");

        // 测试 hashCode
        System.out.println("test hashCode() method:");
        start = System.nanoTime();
        for (int i = 0; i < 500000; i++) {
            proxy.hashCode();
        }
        end = System.nanoTime();
        cost = (end - start) / 1000000;
        System.out.println("took " + cost + " ms\n");

        // 测试 Advised 接口上的方法
        Advised advised = (Advised) proxy;
        System.out.println("test Advised.getProxyTargetClass() method:");
        start = System.nanoTime();
        for (int i = 0; i < 500000; i++) {
            advised.getTargetClass();
        }
        end = System.nanoTime();
        cost = (end - start) / 1000000;
        System.out.println("took " + cost + " ms\n\n");
    }

    public static void main(String[] args) {
        ProxyPerform perform = new ProxyPerform();

        Person person = new Employee();
        Pointcut pointcut = new PersonStaticPointcut();
        Advice advice = new PersonBeforeAdvice();
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, advice);

        perform.cglib(advisor, person);
        perform.cglibFrozen(advisor, person);
        perform.jdk(advisor, person);
    }
}
