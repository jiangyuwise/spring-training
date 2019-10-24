package com.codve.prospring.ch06;

import com.codve.prospring.ch06.entry.User;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 使用 MappingSqlQuery 完成数据到对象的映射
 * @author admin
 * @date 2019/10/24 17:30
 */
public class ListUsers extends MappingSqlQuery<User> {

    private static final String SQL_SELECT_USERS =
            "select `user_id`, `user_name`, `user_birthday` from `user`";


    public ListUsers(DataSource dataSource) {
        super(dataSource, SQL_SELECT_USERS);
    }

    @Override
    protected User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();

        user.setId(rs.getLong("user_id"));
        user.setName(rs.getString("user_name"));
        user.setBirthday(new Date(rs.getLong("user_birthday")));
        return user;
    }
}
