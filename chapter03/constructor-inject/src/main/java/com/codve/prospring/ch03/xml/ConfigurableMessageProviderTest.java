package com.codve.prospring.ch03.xml;

import com.codve.prospring.ch02.decoupled.MessageProvider;
import org.springframework.context.support.GenericXmlApplicationContext;

public class ConfigurableMessageProviderTest {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new
                GenericXmlApplicationContext();
        context.load("classpath:xml/app.xml");
        context.refresh();
        MessageProvider provider = context.getBean("provider",
                ConfigurableMessageProvider.class);
        System.out.println(provider.getMessage());

        context.close();
    }
}
