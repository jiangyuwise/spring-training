package com.codve.prospring.ch06;

import com.codve.prospring.ch06.dao.UserDao;
import com.codve.prospring.ch06.entry.User;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.*;

/**
 * JdbcTemplate 是线程安全的, 多个对象可以使用一个实例
 *
 * 测试 h2.xml 文件
 * h2.xml 中定义了 dataSource , 类型为 h2 内置数据库
 * 使用了测试脚本, h2/table.sql 创建表, h2/data.sql 插入测试数据
 * 使用 CleanUp.java 清除所有的数据和表
 *
 * NamedParameterJdbcTemplate 使用命名参数占位符
 *
 * @author admin
 * @date 2019/10/25 13:35
 */
public class JdbcUserDao implements UserDao, InitializingBean {

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<User> listUsers() {
        String sql ="select `user_id`, `user_name`, `user_birthday` from `user`";
        return jdbcTemplate.query(sql,rs -> {
            List<User> users = new ArrayList<>();
                while (rs.next()) {
                    User user = new User();
                    user.setId(rs.getLong("user_id"));
                    user.setName(rs.getString("user_name"));
                    user.setBirthday(new Date(rs.getLong("user_birthday")));
                    users.add(user);
                }
            return users;
        });
    }

    /**
     * jdbcTemplate.queryForObject(sql, 要传递的参数, 返回的类型)
     * @param id 用户编号
     * @return
     */
    @Override
    public String getNameById(Long id) {
        String sql = "select `user_name` from `user` where `user_id` = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, String.class);
    }

    @Override
    public List<User> listUsersByName(String name) {
        throw new NotImplementedException("listUsers");
    }

    @Override
    public void insert(User user) {
        throw new NotImplementedException("listUsers");
    }

    @Override
    public void update(User user) {
        String sql = "update `user` set `user_name` = :name, `user_birthday` = :birthday " +
                "where `user_id` = :userId";
        Map<String, Object> params = new HashMap<>();
        params.put("name", user.getName());
        params.put("birthday", user.getBirthday().getTime());
        params.put("userId", user.getId());
        namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public void delete(Long id) {
        throw new NotImplementedException("listUsers");
    }

    @Override
    public List<User> listUsersWithArticle() {
        throw new NotImplementedException("listUsers");
    }

    @Override
    public void insertWithArticle(User user) {
        throw new NotImplementedException("listUsers");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (jdbcTemplate == null) {
            throw new BeanCreationException("no JdbcTemplate for UserDao.");
        }
    }
}
