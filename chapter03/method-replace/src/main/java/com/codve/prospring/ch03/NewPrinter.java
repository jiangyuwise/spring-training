package com.codve.prospring.ch03;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

/**
 * 这个类用于替换Printer
 */
public class NewPrinter implements MethodReplacer {

    /**
     *
     * @param obj 要替换的 bean
     * @param method 要替换的方法
     * @param args 方法参数
     * @return 替换后的方法返回的参数
     * @throws Throwable 无法替换时抛出异常
     */
    @Override
    public String reimplement(Object obj, Method method, Object[] args) throws Throwable {
        if (!checkReplace(method)) {
            throw new IllegalArgumentException("unable to replace method " + method.getName());
        }
        String msg = (String) args[0];
        return "** " + msg + " **";
    }

    private boolean checkReplace(Method method) {
        if ( !method.getName().equals("print")
                | method.getParameterCount() != 1
                | method.getReturnType() != String.class
                | method.getParameterTypes()[0] != String.class) {
            return false;
        }
        return true;
    }
}
