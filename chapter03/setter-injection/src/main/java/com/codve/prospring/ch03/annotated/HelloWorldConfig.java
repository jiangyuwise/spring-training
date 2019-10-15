package com.codve.prospring.ch03.annotated;

import com.codve.prospring.ch02.decoupled.MessageProvider;
import com.codve.prospring.ch02.decoupled.MessageRender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 采用纯注解 替换 XML 文件
 * 配合 HelloWorldConfigTest 一起食用
 * @see HelloWorldConfigTest
 */
@Configuration
public class HelloWorldConfig {

    /**
     * 声明一个 Bean, Bean 的名字来自方法名(provider)
     */
    @Bean
    public MessageProvider provider() {
        return new HelloWorldMessageProvider();
    }

    @Bean
    public MessageRender render() {
        MessageRender render = new StandardMessageRender();
        render.setMessageProvider(provider());
        return render;
    }
}
