package com.codve.prospring.ch04;

import org.springframework.aop.framework.ProxyFactory;

public class DBTest {
    public static DB getDB() {
        ProxyFactory factory = new ProxyFactory();
        factory.addAdvice(new DBAdvice());
        factory.setTarget(new DB());

        return (DB) factory.getProxy();
    }

    public static void main(String[] args) {

        DB proxy = getDB();

        // 模拟未登录
        try {
            proxy.delete(1);
        } catch (SecurityException e) {
            System.out.println(e.getMessage());
        }

        // 模拟不正确的登录
        LoginSystem loginSystem = new LoginSystem();
        loginSystem.login("guest", "guest");

        try {
            proxy.delete(1);
        } catch (SecurityException e) {
            System.out.println(e.getMessage());
        } finally {
            loginSystem.logout();
        }

        // 模拟正确的登录
        loginSystem.login("admin", "admin");
        proxy.delete(1);
    }
}
