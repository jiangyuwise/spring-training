package com.codve.prospring.ch04.annotation;

import com.codve.prospring.ch04.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ProfileTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(EmployeeConfig.class, StudentConfig.class);

        Person person = context.getBean("person", Person.class);
        person.info();
        context.close();
    }
}
