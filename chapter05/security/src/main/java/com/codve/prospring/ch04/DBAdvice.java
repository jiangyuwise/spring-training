package com.codve.prospring.ch04;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

/**
 * 在用户操作数据库前插入前置通知
 */
public class DBAdvice implements MethodBeforeAdvice {

    private LoginSystem loginSystem;

    public DBAdvice() {
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
}
