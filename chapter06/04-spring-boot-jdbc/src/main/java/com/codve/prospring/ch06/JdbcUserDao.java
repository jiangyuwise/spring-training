package com.codve.prospring.ch06;

import com.codve.prospring.ch06.dao.UserDao;
import com.codve.prospring.ch06.entry.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author admin
 * @date 2019/10/25 17:44
 */
@Component("userDao")
public class JdbcUserDao implements UserDao{

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> listUsers() {
        return null;
    }

    @Override
    public String getNameById(Long id) {
        String sql = "select `user_name` from `user` where `user_id` = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, String.class);
    }

    @Override
    public List<User> listUsersByName(String name) {
        return null;
    }

    @Override
    public void insert(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<User> listUsersWithArticle() {
        return null;
    }

    @Override
    public void insertWithArticle(User user) {

    }
}
