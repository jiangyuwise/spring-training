package com.codve.prospring.ch03;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Address {
    private String Nation = "China";

    public Address(@Value("America") String nation) {
        Nation = nation;
    }

    public String getNation() {
        return Nation;
    }

    public void setNation(String nation) {
        Nation = nation;
    }
}
