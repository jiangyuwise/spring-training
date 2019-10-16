package com.codve.prospring.ch03.xml;

/**
 * AbstractEmployee 是抽象类
 * Spring 会自动创建子类, 并重写抽象方法
 * 重写后的抽象方法, 每次调用都会返回新的实例
 * 需要在 XML 中声明 lookup-method, 见 xml/app.xml
 * @see MethodInjectTest
 */
public abstract class AbstractEmployee implements Person{

    @Override
    public abstract Address getAddress();

    @Override
    public void info() {
        System.out.println(getAddress().getName());
    }
}
