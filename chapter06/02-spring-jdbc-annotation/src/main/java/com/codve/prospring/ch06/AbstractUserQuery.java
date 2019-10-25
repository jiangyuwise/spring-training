package com.codve.prospring.ch06;

import com.codve.prospring.ch06.entry.User;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author admin
 * @date 2019/10/25 10:29
 */
public abstract class AbstractUserQuery extends MappingSqlQuery<User> {

    public AbstractUserQuery(DataSource ds, String sql) {
        super(ds, sql);
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
