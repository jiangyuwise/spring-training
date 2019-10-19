package com.codve.prospring;

/**
 * 以 Agent.speak() 作为连接点, 在该方法前后插入代码
 */
public class Agent {
    public void speak() {
        System.out.print("Bond");
    }
}
