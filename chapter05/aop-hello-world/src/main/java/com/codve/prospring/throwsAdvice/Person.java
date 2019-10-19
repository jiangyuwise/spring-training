package com.codve.prospring.throwsAdvice;

public class Person {
    public void workException() throws Exception {
        throw new Exception("Generic Exception happened.");
    }

    public void workIllegalException() {
        throw new IllegalArgumentException("Illegal argument.");
    }
}
