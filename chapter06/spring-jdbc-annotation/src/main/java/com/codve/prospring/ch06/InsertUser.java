package com.codve.prospring.ch06;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * @author admin
 * @date 2019/10/24 18:46
 */
public class InsertUser extends SqlUpdate {
    private static final String SQL_INSERT_USER =
            "insert into `user` (`user_name`, `user_birthday`) values (:name, :birthday)";

    public InsertUser(DataSource dataSource) {
        super(dataSource, SQL_INSERT_USER);
        super.declareParameter(new SqlParameter("name", Types.VARCHAR));
        super.declareParameter(new SqlParameter("birthday", Types.BIGINT));
        super.setGeneratedKeysColumnNames("id");
        super.setReturnGeneratedKeys(true);
    }
}
