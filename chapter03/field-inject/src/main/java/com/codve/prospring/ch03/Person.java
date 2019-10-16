package com.codve.prospring.ch03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 使用字段注入, 不需要构造函数和 setter. 需要配合使用 XML 文件 和 Address 食用
 * 其中, 私有字段 Address 必须加 @Autowired
 * SpringIoC 在实例化 person 和 address 时, 会把 address 注入到 person 中.
 * 字段注入使 POJO 与 Spring 紧紧耦合, 且难以单元测试.
 * 非常不推荐使用字段注入,
 * @see Address
 */
@Service("person")
public class Person {

    @Autowired
    private Address address;

    public void info() {
        System.out.println(address.getNation());
    }
}
