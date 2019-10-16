package com.codve.prospring.ch03;

import com.codve.prospring.ch03.annotation.AbstractEmployee;
import com.codve.prospring.ch03.annotation.Employee;
import com.codve.prospring.ch03.annotation.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 测试 查找方法注入
 * 如果所有的 bean 已经注解好了, 就可以用这种方式
 * 效果和 annotation/MethodInjectTest 是一样的.
 * @see com.codve.prospring.ch03.annotation.MethodInjectTest
 *
 * TODO 这里有个错误, 因为 address 依赖 xml 文件的 name, 需要纯 java 完成构造函数注入
 */
public class MethodInjectTest {
    @Configuration
    @ComponentScan(basePackages = "com.codve.prospring.ch03.annotation")
    public static class LookupConfig {

    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(LookupConfig.class);
        Person employee = context.getBean("employee", Employee.class);
        Person abstractEmployee = context.getBean("abstractEmployee", AbstractEmployee.class);

        System.out.println(employee.getAddress());
        System.out.println(abstractEmployee.getAddress());
    }

}
