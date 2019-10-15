package com.codve.prospring.ch03.example;

/**
 * 抽象的车库, 表示抽象容器类
 */
public interface Garage {
    Car getCar(String key);
}
