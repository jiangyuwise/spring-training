package com.codve.prospring.ch04;

/**
 * 模拟登陆系统
 */
public class LoginSystem {
    private static ThreadLocal<UserInfo> threadLocal = new ThreadLocal<>();

    public void login(String name, String pass) {
        threadLocal.set(new UserInfo(name, pass));
    }

    public void logout() {
        threadLocal.set(null);
    }

    public UserInfo getUser() {
        return threadLocal.get();
    }
}
