package com.codve.prospring.ch05;

public class Employee implements Person {
    @Override
    public void work() {
        System.out.println("working");
    }

    @Override
    public void work(int hour) {
        System.out.println("working " + hour + " hours");
    }

    @Override
    public void rest() {
        System.out.println("resting");
    }
}
