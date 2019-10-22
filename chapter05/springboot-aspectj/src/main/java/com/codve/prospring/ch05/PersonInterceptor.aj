package com.codve.prospring.ch05;

public aspect PersonInterceptor {
    pointcut workPointcut():
            execution(* com.codve.prospring.ch05.Person.work());

    before(): workPointcut() {
        System.out.println("prepare");
    }

    after(): workPointcut() {
        System.out.println("finished.");
    }
}