package com.codve.prospring.ch03.annotated;

import com.codve.prospring.ch02.decoupled.MessageRender;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 使用纯 java 注解完成配置
 * @see HelloWorldConfig
 */
public class HelloWorldConfigTest {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(HelloWorldConfig.class);
        MessageRender render = context.getBean("render", MessageRender.class);
        render.render();

        ApplicationContext context2 =
                new AnnotationConfigApplicationContext(HelloWorldConfig2.class);
        MessageRender render2 = context2.getBean("render", MessageRender.class);
        render2.render();

        ApplicationContext context3 =
                new AnnotationConfigApplicationContext(HelloWorldConfig3.class);
        MessageRender render3 = context3.getBean("render", MessageRender.class);
        render3.render();

    }
}
