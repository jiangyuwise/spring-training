package com.codve.prospring.ch03.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 使用注解的方式实现 查找方法注入
 */
@Component("address")
@Scope("prototype")
public class Address {
    private String name;

    @Autowired
    public Address(@Value("Beijing") String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
