package com.codve.prospring.ch06;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * 清除 H2 数据库的所有的数据和表
 * @author admin
 * @date 2019/10/25 12:06
 */
public class CleanUp {
    private JdbcTemplate jdbcTemplate;

    public CleanUp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void destroy() {
        jdbcTemplate.execute("drop all objects delete files;");
    }
}
