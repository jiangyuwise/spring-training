package com.codve.prospring.ch04;

import org.springframework.aop.support.DefaultIntroductionAdvisor;

// 保证每个实例都是新的
public class IsModifiedAdvisor extends DefaultIntroductionAdvisor {
    public IsModifiedAdvisor() {
        super(new PersonInterceptor());
    }
}
