package com.codve.prospring.ch04;

import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ControlFlowPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * 创建控制流切入点
 * 只有从特定的方法使用代理时, 切入点才会有效.
 * 实际开发中, 比如规定从某个地方调用购买商品, 才会赠送积分.
 */
public class ControlFlowTest {

    public void test(Person person) {
        person.work();
    }

    public static void main(String[] args) {
        ControlFlowTest controlFlow = new ControlFlowTest();
        Person person = new Employee();

        // 只有ControlFlowTest.test 方法内, Employee.work 的切入点才会生效
        Pointcut pointcut = new ControlFlowPointcut(ControlFlowTest.class, "test");
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, new PersonBeforeAdvice());

        ProxyFactory factory = new ProxyFactory();
        factory.addAdvisor(advisor);
        factory.setTarget(person);

        Person proxy = (Person) factory.getProxy();

        // 无效的切入点
        proxy.work();

        // 有效的切入点
        controlFlow.test(proxy);
    }
}
