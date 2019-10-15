package com.codve.prospring.ch02.annotated;

import com.codve.prospring.ch02.decoupled.HelloWorldMessageProvider;
import com.codve.prospring.ch02.decoupled.MessageProvider;
import com.codve.prospring.ch02.decoupled.MessageRender;
import com.codve.prospring.ch02.decoupled.StandardMessageRender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 使用@Configuration 代替 XML 文件, 效果等同于 xml/app.xml
 * 读取该配置的类为 AnnotationConfigApplicationContext
 */
@Configuration
public class HelloWorldConfig {

    // 等同于 <bean id="provider" class="">
    @Bean
    public MessageProvider provider() {
        return new HelloWorldMessageProvider();
    }

    // 等同于 <bean id="render" class="" p:messageRender="provider">
    @Bean
    public MessageRender render() {
        MessageRender render = new StandardMessageRender();
        render.setMessageProvider(provider());
        return render;
    }
}
