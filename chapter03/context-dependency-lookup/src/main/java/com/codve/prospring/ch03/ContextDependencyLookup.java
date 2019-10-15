package com.codve.prospring.ch03;

/**
 * 具体的组件, 它有一个依赖, 并且实现了从容器获取依赖的方法.
 */
public class ContextDependencyLookup implements ManagedComponent {

    private Dependency dependency; // 委托的适配器

    @Override
    public void performLookup(Container container) {
        dependency = (Dependency) container.getDependency("myDependency");
    }

    @Override
    public String toString() {
        return dependency.toString();
    }
}
