package com.codve.prospring;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;

/**
 * 使用 AOP 编程
 * AgentDecorator implements MethodInterceptor extends Agent
 */
public class AgentDecorator implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.print("James "); // 在连接点前插入代码

        Object retVal = invocation.proceed();

        System.out.println("!"); // 在连接点后插入代码
        return retVal;
    }

    public static void main(String[] args) {
        Agent target = new Agent();

        ProxyFactory factory = new ProxyFactory(); // 代理工厂
        factory.addAdvice(new AgentDecorator());
        factory.setTarget(target);

        Agent proxy = (Agent) factory.getProxy(); // 实际上, 切片编程就是创建子类

        target.speak(); // 原始类的调用
        System.out.println();
        proxy.speak(); // 子类的调用

    }
}
