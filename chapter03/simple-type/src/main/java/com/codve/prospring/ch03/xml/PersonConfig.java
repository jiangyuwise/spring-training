package com.codve.prospring.ch03.xml;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component("personConfig")
public class PersonConfig {
    private String name = "Jimmy";
    private int age = 24;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
