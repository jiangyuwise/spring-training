package com.codve.prospring.ch04;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class EnvTest {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.refresh();

        ConfigurableEnvironment env = context.getEnvironment();
        MutablePropertySources propertySources = env.getPropertySources();

        Map<String, Object> config = new HashMap<>();
        config.put("application.home", "application_home");

        // 添加环境变量, 会影响到 env
        propertySources.addFirst(new MapPropertySource("userConfig", config));

        // 获取环境变量
        //获取 System 的值
        System.out.println("user.home: " + System.getProperty("user.home")); // Users/admin
        System.out.println("JAVA_HOME: " + System.getenv("JAVA_HOME")); // null

        //获取 env 的值
        System.out.println("user.home: " + env.getProperty("user.name")); // admin
        System.out.println("JAVA_HOME: " + env.getProperty("JAVA_HOME")); // null
        System.out.println("application.home: " + env.getProperty("application.home"));

        context.close();
    }
}
