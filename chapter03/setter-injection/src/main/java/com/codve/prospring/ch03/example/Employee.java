package com.codve.prospring.ch03.example;

public class Employee implements Person {

    private Address address;

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String getAddress() {
        return "Beijing, China";
    }
}
