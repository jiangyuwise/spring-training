package com.codve.prospring;

import com.codve.prospring.ctrl.HelloWorld;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

/**
 * 访问地址: localhost:8080
 */
@SpringBootApplication(scanBasePackageClasses = HelloWorld.class)
public class WebApplication {
    private static Logger logger = LoggerFactory.getLogger(WebApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(WebApplication.class);
        assert (context != null);
        logger.info("start web application");

        // 终端输入字符后, 结束 ApplicationContext
        try {
            System.in.read();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        context.close();
        context.close();
    }
}
