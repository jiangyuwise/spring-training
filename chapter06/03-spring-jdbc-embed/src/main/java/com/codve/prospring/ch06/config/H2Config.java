package com.codve.prospring.ch06.config;

import com.codve.prospring.ch06.CleanUp;
import com.codve.prospring.ch06.JdbcUserDao;
import com.codve.prospring.ch06.dao.UserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * 使用注解代替 h2.xml 文件
 * @author admin
 * @date 2019/10/25 14:04
 */
public class H2Config {

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        builder.setType(EmbeddedDatabaseType.H2);
        builder.addScript("classpath:h2/table.sql");
        builder.addScript("classpath:h2/data.sql");
        return builder.build();
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());

        // 设置错误码转换, 但不知效果行不行.
/*        MysqlErrorCodeTranslator errorCodeTranslator = new MysqlErrorCodeTranslator();
        errorCodeTranslator.setDataSource(dataSource());
        jdbcTemplate.setExceptionTranslator(errorCodeTranslator);*/

        return jdbcTemplate;
    }

    @Bean
    public UserDao userDao() {
        JdbcUserDao userDao = new JdbcUserDao();
        userDao.setJdbcTemplate(jdbcTemplate());
        return userDao;
    }

    @Bean(destroyMethod = "destroy")
    public CleanUp cleanUp() {
        return new CleanUp(jdbcTemplate());
    }
}
