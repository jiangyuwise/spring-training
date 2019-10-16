package com.codve.prospring.ch03.xml;

/**
 * XML 中一定要加 scope="prototype", 否则 spring 将返回缓存的 bean
 * scope 的默认值是"singleton", 返回的都是单例模式,
 * scope="prototype" 表示每次 getBean 都会返回新的实例
 */
public class Address {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
