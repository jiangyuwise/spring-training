package com.codve.prospring.ch05;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 使用 注解代替app.xml
 */
@Configuration
@ComponentScan(basePackages = "com.codve.prospring.ch05")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AnnotationConfigTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AnnotationConfigTest.class);

        Person person = context.getBean("person", Person.class);
        person.work(3);
        person.work(8);
        context.close();
    }
}
