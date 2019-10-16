package com.codve.prospring.ch03.xml;

/**
 * XML 中一定要加 scope="prototype", 否则 spring 将返回缓存的 bean
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
