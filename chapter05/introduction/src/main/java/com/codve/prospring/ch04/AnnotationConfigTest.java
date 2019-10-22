package com.codve.prospring.ch04;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 使用注解代替 xml 创建 introduction
 */
public class AnnotationConfigTest {
    @Bean
    public Person person() {
        Person person = new Person();
        person.setName("Jimmy");
        return person;
    }

    @Bean
    public Advisor advisor() {
        return new IsModifiedAdvisor();
    }

    @Bean
    public ProxyFactoryBean proxy() {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.addAdvisor(advisor());
        proxyFactoryBean.setTarget(person());
        proxyFactoryBean.setProxyTargetClass(true);
        proxyFactoryBean.setOptimize(true);
        return proxyFactoryBean;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AnnotationConfigTest.class);
        Person proxy = context.getBean("proxy", Person.class);
        IsModified isModified = (IsModified) proxy;

        proxy.setName("Jimmy");
        System.out.println(isModified.isModified()); // false

        proxy.setName("James");
        System.out.println(isModified.isModified()); // true

        proxy.setName("James");
        System.out.println(isModified.isModified()); // false
        context.close();
    }
}
