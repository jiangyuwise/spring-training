package com.codve.prospring.ch03;

public class Son {

    private Parent parent;

    private String name = "little Tom";

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void info() {
        System.out.println("name: " + name + ", parent: " + parent.getName());
    }
}
