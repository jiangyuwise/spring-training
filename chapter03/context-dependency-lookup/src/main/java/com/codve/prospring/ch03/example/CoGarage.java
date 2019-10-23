package com.codve.prospring.ch03.example;

/**
 * 具体的车库, 表示具体的容器类
 */
public class CoGarage implements Garage {

    @Override
    public Car getCar(String key) {
        if ("bmw".equals(key)) {
            return new BMW();
        } else if ("audi".equals(key)) {
            return new Audi();
        }
        else {
            throw new RuntimeException("there is no car you want.");
        }
    }
}
