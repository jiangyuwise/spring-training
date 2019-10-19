package com.codve.prospring.ch04;

public class UserInfo {
    private String name;
    private String pass;

    public UserInfo(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public String getPass() {
        return pass;
    }
}
