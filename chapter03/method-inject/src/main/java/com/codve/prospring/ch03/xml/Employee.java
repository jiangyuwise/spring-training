package com.codve.prospring.ch03.xml;

/**
 * 使用 setter 注入 Address
 * 在 employee 实例的整个生命周期中, address 都是不变的.
 */
public class Employee implements Person{

    private Address address;

    @Override
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public void info() {
        System.out.println(address.getName());
    }
}
