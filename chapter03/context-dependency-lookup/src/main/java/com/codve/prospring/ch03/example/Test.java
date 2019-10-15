package com.codve.prospring.ch03.example;

public class Test {
    public static void main(String[] args) {
        Garage garage = new CoGarage();
        CoDriver driver = new CoDriver();

        driver.lookup(garage);

        System.out.println(driver.drive());
    }
}
