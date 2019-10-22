package com.codve.prospring.ch05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication()
public class Application {
    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        assert (context != null);

        Person person = context.getBean("person", Person.class);
        // 检查前置通知
        person.work(3);
        person.work(8);

        // 检查后置通知
        person.work();
        person.rest();

        Employee employee = context.getBean("employee", Employee.class);
        employee.work();

        context.close();
    }
}
