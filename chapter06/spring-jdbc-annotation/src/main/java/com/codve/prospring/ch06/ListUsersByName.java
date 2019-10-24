package com.codve.prospring.ch06;

import com.codve.prospring.ch06.entry.User;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * @author admin
 * @date 2019/10/24 18:24
 */
public class ListUsersByName extends MappingSqlQuery<User> {
    private static final String SQL_LIST_USERS_BY_NAME =
            "select `user_id`, `user_name`, `user_birthday` " +
                    "from `user` where `user_name` like :name";

    public ListUsersByName(DataSource dataSource) {
        super(dataSource, SQL_LIST_USERS_BY_NAME);
        super.declareParameter(new SqlParameter("name", Types.VARCHAR));
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
