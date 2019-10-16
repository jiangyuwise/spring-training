package com.codve.prospring.ch03.annotation;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

/**
 * 使用注解的方式实现 查找方法注入
 * 不需要抽象类和抽象方法, 只需要 @Lookup 注解
 */
@Component("abstractEmployee")
public class AbstractEmployee implements Person{

    @Override
    @Lookup("address")
    public Address getAddress() {
        return null;
    }

    @Override
    public void info() {
        System.out.println(getAddress().getName());
    }
}
