package com.codve.prospring.ch04;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Locale;

/**
 * 使用国际化资源
 * 见 app.xml, message_zh.properties, message_en.properties
 */
public class MessageSourceTest {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:app.xml");
        context.refresh();

        Locale english = Locale.ENGLISH;
        Locale chinese = Locale.CHINESE;

        System.out.println(context.getMessage("msg", null, english));
        System.out.println(context.getMessage("msg", null, chinese));

        // 使用占位符
        System.out.println(context.getMessage("msg1", new Object[]{"John", "Smith"}, english));
        System.out.println(context.getMessage("msg1", new Object[]{"John", "Smith"}, chinese));


        context.close();
    }
}
