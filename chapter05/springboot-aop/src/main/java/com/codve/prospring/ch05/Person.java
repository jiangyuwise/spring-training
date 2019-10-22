package com.codve.prospring.ch05;

import org.springframework.stereotype.Component;

@Component("person")
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
