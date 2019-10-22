package com.codve.prospring.ch05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("employee")
public class Employee {
    private Person person;

    @Autowired
    public void setPerson(Person person) {
        this.person = person;
    }

    public void work() {
        person.work();
        person.rest();
    }
}
