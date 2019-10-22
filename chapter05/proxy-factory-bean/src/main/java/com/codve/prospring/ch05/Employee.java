package com.codve.prospring.ch05;

// 使用委托的适配器
public class Employee {
    private Person person;

    public void setPerson(Person person) {
        this.person = person;
    }

    public void work() {
        person.work();
        person.rest();
    }
}
