package com.codve.prospring.ch04;

import com.codve.prospring.ch02.decoupled.MessageProvider;
import com.codve.prospring.ch02.decoupled.MessageRender;
import com.codve.prospring.ch02.decoupled.StandardMessageRender;
import com.codve.prospring.ch03.xml.ConfigurableMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

/**
 * 通过 java 注解访问资源
 */
@Configuration
@PropertySource(value = "classpath:msg.properties")
public class ResourceConfig {

    @Autowired
    Environment env;

    @Bean
    @Lazy
    public MessageProvider messageProvider() {
        return new ConfigurableMessageProvider(env.getProperty("msg"));
    }

    @Bean("render")
    @Scope(value = "prototype")
    @DependsOn(value = "messageProvider")
    public MessageRender messageRender() {
        MessageRender render = new StandardMessageRender();
        render.setMessageProvider(messageProvider());
        return render;
    }

    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(ResourceConfig.class);
        MessageRender render = context.getBean("render", MessageRender.class);
        render.render();
    }

}
