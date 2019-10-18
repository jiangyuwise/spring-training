package com.codve.prospring.ch04.annotation;

import com.codve.prospring.ch04.Employee;
import com.codve.prospring.ch04.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("employee")
public class EmployeeConfig {
    @Bean
    public Person person() {
        return new Employee("Anna", "developer");
    }
}
