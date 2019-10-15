package com.codve.prospring.ch03;

/**
 * 具体的容器
 */
public class DefaultContainer implements Container {

    @Override
    public Object getDependency(String key) {
        if ("myDependency".equals(key)) {
            return new Dependency();
        }
        throw new RuntimeException("unknown dependency: " + key);
    }
}
