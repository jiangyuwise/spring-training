package com.codve.prospring.ch06;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * spring 中的 $ 和 #.
 * 1. ${key} 用于获取 properties 文件中 key 的值, 可以出现在 XML 或者@Value()中.
 *
 * 2. #{} 是 SpEL 表达式
 * @author admin
 * @date 2019/10/24 15:26
 */
public class DataSourceTest {

    private static Logger logger = LoggerFactory.getLogger(DataSourceTest.class);

    /**
     * 使用org.apache.commons.dbcp2.BasicDataSource
     * BasicDataSource 提供连接池
     * dataSource 定义在 app.xml 中, app.xml 通过 util:properties
     * 加载了main/resources/db/jdbc2.properties.
     *
     * util.properties 会创建一个新的 property bean, 它的 key 全来自配置文件,
     * 所以获取 key 时使用了SpEL 表达式
     */
    @Test
    public void test1() {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:app.xml");
        context.refresh();

        DataSource dataSource = context.getBean("dataSource", BasicDataSource.class);
        assertNotNull(dataSource);

        selectTest(dataSource);
        context.close();
    }

    /**
     * 使用 org.springframework.jdbc.datasource.DriverManagerDataSource
     * DriverManagerDataSource 没有连接池
     * dataSource 定义在 app2.xml 中, 通过context:property-placeholder
     * 加载了 main/resources/db/jdbc.properties
     * 直接使用 ${key} 获取 key
     *
     * 注意子项目的 build.gradle 有 mysql 依赖
     */
    @Test
    public void test2() {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:app2.xml");
        context.refresh();

        DataSource dataSource = context.getBean("dataSource", DataSource.class);
        assertNotNull(dataSource);

        selectTest(dataSource);
        context.close();
    }

    /**
     * 使用 org.springframework.jdbc.datasource.DriverManagerDataSource
     * 使用新生成的 properties bean
     */
    @Test
    public void test3() {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:app3.xml");
        context.refresh();

        DataSource dataSource = context.getBean("dataSource", DataSource.class);
        assertNotNull(dataSource);

        selectTest(dataSource);
        context.close();
    }

    /**
     * 测试用注解代替 XML
     * @see DataSourceConfig
     */
    @Test
    public void test4() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DataSourceConfig.class);

        DataSource dataSource = context.getBean("dataSource", DataSource.class);
        assertNotNull(dataSource);

        selectTest(dataSource);
        context.close();
    }

    private void selectTest(DataSource dataSource) {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement("select 1");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int result = resultSet.getInt("1");
                assertEquals(1, result);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }
}