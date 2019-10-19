package com.codve.prospring.ch04;

public class Student implements Person {
    private String name;
    private String major;

    public Student(String name, String major) {
        this.name = name;
        this.major = major;
    }

    @Override
    public void info() {
        System.out.println("name: " + name + ", major: " + major);
    }
}
