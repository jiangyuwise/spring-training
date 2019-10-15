package com.codve.prospring.ch03;

/**
 * 抽象组件, 声明了一个方法, 可以利用容器查找依赖
 */
public interface ManagedComponent {
    void performLookup(Container container);
}
