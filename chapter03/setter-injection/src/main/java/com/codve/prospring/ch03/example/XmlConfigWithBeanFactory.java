package com.codve.prospring.ch03.example;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

/**
 * 使用 BeanFactory, BeanDefinitionReader, xml 配置文件完成注入
 */
public class XmlConfigWithBeanFactory {
    public static void main(String[] args) {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);

        reader.loadBeanDefinitions(
                new ClassPathResource("/example/xml-config-with-bean-factory.xml"));

        Person person = factory.getBean("person", Employee.class);
        System.out.println(person.getAddress());
    }
}
