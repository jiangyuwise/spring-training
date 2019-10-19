package com.codve.prospring.ch04;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

/**
 * 在用户操作数据库前插入前置通知
 */
public class User implements MethodBeforeAdvice {

    private LoginSystem loginSystem;

    public User() {
        this.loginSystem = new LoginSystem();
    }

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        UserInfo userInfo = loginSystem.getUser();
        if (userInfo == null) {
            throw new SecurityException("you must login.");
        } else if (userInfo.getName().equals("admin") && (userInfo.getPass().equals("admin"))) {
            System.out.println("permission granted. ");
        } else {
            throw new SecurityException("login failed.");
        }
    }

    public static void main(String[] args) {
        /**
         * 数据库操作前, 判断用户信息
         */
        DB db = new DB();
        User person = new User();

        ProxyFactory factory = new ProxyFactory();
        factory.addAdvice(person);
        factory.setTarget(db);

        DB proxy = (DB) factory.getProxy();

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
