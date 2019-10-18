package com.codve.prospring.ch04.annotation;

import com.codve.prospring.ch04.Person;
import com.codve.prospring.ch04.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * 使用注解的方式代替 xml 文件中的 profile
 * 运行时同样添加参数 -Dspring.profiles.active=student
 */
@Configuration
@Profile("student")
public class StudentConfig {
    @Bean
    public Person person() {
        return new Student("Jimmy", "CS");
    }
}
