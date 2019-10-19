package com.codve.prospring.ch04;

public class Employee implements Person {
    private String  name;
    private String job;

    public Employee(String name, String job) {
        this.name = name;
        this.job = job;
    }

    @Override
    public void info() {
        System.out.println("name: " + name + ", job: " + job);
    }
}
