package com.codve.prospring.ch05;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 创建前置通知和环绕通知
 */
public class PersonInterceptor {
    public void before(int hour) {
        if (hour > 3) {
            System.out.println("prepare for the " + hour + " hours' work.");
        }
    }

    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("prepare");

        Object obj = joinPoint.proceed();

        System.out.println("finish.");
        return obj;
    }
}
