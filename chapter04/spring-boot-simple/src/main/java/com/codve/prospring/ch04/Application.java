package com.codve.prospring.ch04;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.Arrays;

/**
 * 使用 spring boot 启动 ApplicationContext
 * 注解 @SpringBootApplication只能用在类上, 相当于@Configuration + @EnableAutoConfiguration
 * + @ComponentScan
 * EnableAutoConfiguration 来自org.springframework.boot.autoconfigure, 可以启动 Spring ApplicationContext
 * ComponentScan 可以使用属性 basePackages 或者 basePackageClasses(类型安全) 指定Spring boot 要扫描的包
 * 如果没有指明包, 默认扫描注解的类所在的包, 这就是为啥能找到 HelloWorld 的原因
 * Logger 的配置文件为 logback.xml
 */
@SpringBootApplication
public class Application {
    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                SpringApplication.run(Application.class, args);
        assert (context != null); // 检查 context
        logger.info("all beans defined: ");

        // 列出spring boot 预定义的 bean
        Arrays.stream(context.getBeanDefinitionNames()).forEach(logger::warn);

        HelloWorld helloWorld = context.getBean(HelloWorld.class);
        helloWorld.sayHi();
        context.close();
    }
}
