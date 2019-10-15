package com.codve.prospring.ch02.annotated;

import com.codve.prospring.ch02.decoupled.MessageRender;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.*;

public class HelloWorldConfigTest {
    @Test
    public void whenContextInitThenBeanInit() {
        ApplicationContext context = new
                AnnotationConfigApplicationContext(HelloWorldConfig.class);
        MessageRender render = context.getBean("render", MessageRender.class);
        assertNotNull(render);
    }

}