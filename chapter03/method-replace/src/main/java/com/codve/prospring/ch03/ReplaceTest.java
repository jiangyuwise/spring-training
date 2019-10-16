package com.codve.prospring.ch03;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.util.StopWatch;

/**
 * 如果调用Printer.print(String), 就会调用 NewPrinter.print(string)
 * NewPrinter implements MethodReplacer extends Printer
 * NewPrinter 的代码由 Spring CGLIB 动态生成
 */
public class ReplaceTest {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:app.xml");
        context.refresh();

        Printer printer = context.getBean("printer", Printer.class);

        // 注意!!! newPrinter 取的是第3个 bean, 而不是 newPrinter
        Printer newPrinter = context.getBean("replacer", Printer.class);

        String msg = "hello, world";

        System.out.println(printer.print(msg));
        System.out.println(newPrinter.print(msg));
    }
}