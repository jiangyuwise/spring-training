package com.codve.prospring.ch05;

public class Employee implements Person{
    private long timestamp;

    @Override
    public void work() {
        timestamp = System.currentTimeMillis();
    }

    @Override
    public void rest() {
        timestamp = System.currentTimeMillis();
    }
}
