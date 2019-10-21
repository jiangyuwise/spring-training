package com.codve.prospring.ch04;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class PersonAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("prepare");
        Object obj = invocation.proceed();
        System.out.println("work finished.");
        return obj;
    }
}
