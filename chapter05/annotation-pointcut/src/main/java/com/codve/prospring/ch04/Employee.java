package com.codve.prospring.ch04;

/**
 * 使用自定义的注解创建切入点
 */
public class Employee implements Person {

    @Override
    public void work() {
        System.out.println("working");
    }

    @Override
    public void rest() {
        System.out.println("resting");
    }

    @Override
    @AdviceAnnotation
    public void work(int hour) {
        System.out.println("employee is working " + hour + " hours.");
    }
}
