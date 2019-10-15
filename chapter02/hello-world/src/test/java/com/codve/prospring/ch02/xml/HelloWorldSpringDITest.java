package com.codve.prospring.ch02.xml;

import com.codve.prospring.ch02.decoupled.MessageRender;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class HelloWorldSpringDITest {
    @Test
    public void whenContextInitThenBeanInit() {
        ApplicationContext context = new
                ClassPathXmlApplicationContext("/xml/app.xml");
        MessageRender render = context.getBean("render", MessageRender.class);
        assertNotNull(render);
    }
}