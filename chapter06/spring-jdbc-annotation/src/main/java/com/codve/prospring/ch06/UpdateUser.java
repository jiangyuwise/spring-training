package com.codve.prospring.ch06;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * @author admin
 * @date 2019/10/24 19:03
 */
public class UpdateUser extends SqlUpdate {
    private static final String SQL_UPDATE_USER =
            "update `user` set `user_name` = :name, `user_birthday` = :birthday" +
                    " where `user_id` = :id";

    public UpdateUser(DataSource dataSource) {
        super(dataSource, SQL_UPDATE_USER);
        super.declareParameter(new SqlParameter("name", Types.VARCHAR));
        super.declareParameter(new SqlParameter("birthday", Types.BIGINT));
        super.declareParameter(new SqlParameter("id", Types.BIGINT));
    }
}
