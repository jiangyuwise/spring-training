package com.codve.prospring.ch03;

public class Test {
    public static void main(String[] args) {
        Container container = new DefaultContainer();

        ManagedComponent component = new ContextDependencyLookup();
        component.performLookup(container);

        System.out.println(component);
    }
}
