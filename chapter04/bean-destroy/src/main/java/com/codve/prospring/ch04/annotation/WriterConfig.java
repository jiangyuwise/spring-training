package com.codve.prospring.ch04.annotation;

import com.codve.prospring.ch04.Writer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * 使用 java 配置替换 XML 文件
 * 使用 @Bean(destroyMethod) 定义 bean, 其中 destroyMethod 表示 bean 销毁前要执行的方法
 *
 * 多种方式可以使用在一个 bean 上, 执行的顺序如下
 * 1. @PreDestroy
 * 2. DisposableBean 接口的 destroy 方法
 * XML 或者 @Bean 中指定的 destroy-method
 *
 */
@Configuration
public class WriterConfig {

    @Lazy
    @Bean(initMethod = "afterPropertiesSet", destroyMethod = "destroy")
    Writer writer() {
        Writer writer = new Writer();
        writer.setFilepath(System.getProperty("java.io.tmpdir") +
                System.getProperty("file.separator") + "text.txt");
        return writer;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(WriterConfig.class);
        Writer writer = context.getBean("writer", Writer.class);

        context.close();
    }
}
