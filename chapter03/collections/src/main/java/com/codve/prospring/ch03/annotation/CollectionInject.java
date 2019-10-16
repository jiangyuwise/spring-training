package com.codve.prospring.ch03.annotation;

import com.codve.prospring.ch03.xml.Fruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 集合注入, 在 annotation/xml 中定义集合元素,
 * 使用 @Service 注解定义 bean
 * 使用 @Resource 将 xml 中的 bean 注入到字段中, @Resource 的 name 字段匹配 bean 的 id 字段
 * 不需要使用getter 和 setter
 * 可以使用@Qualifier("name") 替换 @Resource
 */
@Service("collectionInject")
public class CollectionInject {

    @Resource(name = "fruits")
    private Map<String, Fruit> fruits;

//    @Autowired
//    @Qualifier("names")
    @Resource(name = "names")
    private List<String> names;

    public void info() {
        fruits.entrySet().stream().forEach(e -> {
            System.out.println(e.getValue().getName());
        });
        System.out.println();

        for (String name : names) {
            System.out.println(name);
        }
    }

    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:annotation/app.xml");
        context.refresh();

        CollectionInject inject = context.getBean("collectionInject", CollectionInject.class);
        inject.info();

        context.close();
    }
}
