package com.codve.prospring.ch03;

/**
 * 在 setter 中注入依赖
 * setter 注入是使用最广泛, 且最简单的注入方式.
 */
public class SetterInject {
    private Dependency dependency;

    public void setDependency(Dependency dependency) {
        this.dependency = dependency;
    }

    @Override
    public String toString() {
        return dependency.toString();
    }
}
