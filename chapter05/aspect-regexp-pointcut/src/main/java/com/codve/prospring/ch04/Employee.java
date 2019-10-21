package com.codve.prospring.ch04;

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
    public void work(int hour) {
        System.out.println("employee is working " + hour + " hours.");
    }
}
