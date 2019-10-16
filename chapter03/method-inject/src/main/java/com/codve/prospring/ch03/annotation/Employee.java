package com.codve.prospring.ch03.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * 使用注解的方式实现 查找方法注入
 * 这里只能使用 @Component 注解, 且setAddress 必须使用@Autowired 和@Qualifier.
 */
@Component("employee")
public class Employee implements Person {

    private Address address;

    @Override
    public Address getAddress() {
        return address;
    }

    @Autowired
    @Qualifier("address")
    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public void info() {
        System.out.println(address.getName());
    }
}
