package com.codve.prospring.ch03.xml;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 集合注入, 配合 collection/app.xml 使用
 * 可以注入简单的集合, 如 Map<String, String>
 * 也可以注入复杂的集合, 如 Map<String, Fruit>, 其中 Fruit 需要在 xml 中定义为 bean.
 *
 */
public class CollectionInject {

    private Map<String, String> map;

    private Properties properties;

    private Set<String> set;

    private List<String> list;

    private Map<String, Fruit> fruits;

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setFruits(Map<String, Fruit> fruits) {
        this.fruits = fruits;
    }

    public void info() {
        // 输出 map
        map.entrySet().stream().forEach(e -> {
            String msg = String.format("key: %s, value: %s", e.getKey(), e.getValue());
            System.out.println(msg);
        });
        System.out.println();

        // 输出 properties
        properties.entrySet().stream().forEach(e -> {
            String msg = String.format("key: %s, value: %s", e.getKey(), e.getValue());
            System.out.println(msg);
        });
        System.out.println();

        // 输出 set
        for (String value: set) {
            System.out.println("value: " + value);
        }
        System.out.println();

        // 输出 list
        for (String value : list) {
            System.out.println("value: " + value);
        }

        // 输出 fruits
        fruits.entrySet().stream().forEach(e -> {
            String msg = String.format("key: %s, value: %s", e.getKey(), e.getValue().getName());
            System.out.println(msg);
        });
    }

    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:xml/app.xml");
        context.refresh();

        CollectionInject inject = context.getBean("collectionInject", CollectionInject.class);
        inject.info();

        context.close();

    }
}
