package com.codve.prospring.ch03;

/**
 * 构造函数依赖注入
 */
public class ConstructorInject {
    private Dependency dependency;

    public ConstructorInject(Dependency dependency) {
        this.dependency = dependency;
    }

    @Override
    public String toString() {
        return dependency.toString();
    }
}
