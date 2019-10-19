package com.codve.prospring.ch04;

public class Employee implements Person {
    @Override
    public void work(int hour) {
        System.out.println("employee is working " + hour + " hours.");
    }
}
