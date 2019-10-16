package com.codve.prospring.ch03.xml;

/**
 * Apple 使用了构造函数注入, 见 xml/app1.xml
 */
public class Apple implements Fruit{
    private String name;

    public Apple(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
