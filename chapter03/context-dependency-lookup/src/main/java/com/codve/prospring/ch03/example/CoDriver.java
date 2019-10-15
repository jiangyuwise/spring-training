package com.codve.prospring.ch03.example;

public class CoDriver implements Driver {
    private Car car;

    @Override
    public void lookup(Garage garage) {
        car = garage.getCar("bmw");
    }

    public String drive() {
        return "I will drive with " + car;
    }
}
