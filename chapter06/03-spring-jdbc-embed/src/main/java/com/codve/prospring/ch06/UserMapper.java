package com.codve.prospring.ch06;

import com.codve.prospring.ch06.entry.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author admin
 * @date 2019/10/25 16:40
 */
public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("user_id"));
        user.setName(rs.getString("user_name"));
        user.setBirthday(new Date(rs.getLong("user_birthday")));
        return user;
    }
}
