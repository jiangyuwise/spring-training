package com.codve.prospring.ch03;

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
        reader.loadBeanDefinitions(new ClassPathResource("/xml-config-with-bean-factory.xml"));
        Oracle oracle = factory.getBean("oracle", BookWormOracle.class);
        System.out.println(oracle.defineMeaningOfLife());
    }
}
