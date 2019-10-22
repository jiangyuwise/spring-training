package com.codve.prospring.ch05;

public class Person {

    public void work(int hour) {
        System.out.println("working for " + hour + " hours");
    }

    public void work() {
        System.out.println("working...");
    }

    public void rest() {
        System.out.println("rest");
    }
}
