package com.codve.prospring.ch05;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 添加 springboot starter-aop 后, 默认支持 AOP
 * 无需再设置<aop:aspectj-autoproxy/>
 */
@Component
@Aspect
public class PersonInterceptor {
    //创建切入点
    @Pointcut("execution(* com.codve.prospring.ch05.Person.work*(int)) && args(hour)")
    public void pointcut1(int hour) {

    }

    @Pointcut("execution(* com.codve.prospring.ch05.Person.work*())")
    public void pointcut2() {

    }

    // 创建前置通知, @Before 指定切入点
    @Before("pointcut1(hour)")
    public void before(int hour) {
        if (hour > 3) {
            System.out.println("prepare for the " + hour + " hours' work");
        }
    }

    // 创建环绕通知
    @Around("pointcut2()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("prepare");

        Object obj = joinPoint.proceed();

        System.out.println("finished");
        return obj;
    }
}
