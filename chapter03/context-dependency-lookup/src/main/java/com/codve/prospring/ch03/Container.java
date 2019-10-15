package com.codve.prospring.ch03;

/**
 * 抽象的容器, 声明了一个方法: 可以根据 key 查找对于的依赖.
 * 容器负责管理依赖, 容器通常由底层服务提供, 如 spring
 */
public interface Container {
    Object getDependency(String key);
}
