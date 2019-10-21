package com.codve.prospring.ch04.annotation;

import com.codve.prospring.ch04.Person;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * 使用 java 配置替换 XML 文件
 * 使用 @Bean(initMethod) 定义 bean, 其中 initMethod 指定初始化后执行的方法
 * 使用 @Lazy 表示 lazy-init
 *
 * 多种方式可以用在同一个 bean 上, 生效的顺序为
 * 1. @PostConstruct 注解
 * 2. InitializingBean 接口的 afterProperties()
 * 3. xml 的 init-method 或者 @Bean(initMethod)
 */
@Configuration
public class PersonConfig {

    // 定义 id="person1" 的 bean
    @Lazy
    @Bean(initMethod = "init")
    Person person1() {
        Person person1 = new Person();
        person1.setName("Jimmy");
        person1.setAge(22);
        return person1;
    }

    // 定义 id="person2" 的 bean
    @Lazy
    @Bean(initMethod = "init")
    Person person2() {
        Person person2 = new Person();
        person2.setAge(24);
        return person2;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(PersonConfig.class);

        Person person1 = context.getBean("person1", Person.class);
        person1.info();

        try {
            Person person2 = context.getBean("person2", Person.class);
            person2.info();
        } catch (BeanCreationException e) {
            System.out.println(e.getMessage());
        }

        context.close();
    }
}
