package com.codve.prospring.ch06;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * @author admin
 * @date 2019/10/24 19:16
 */
public class DeleteUserUpdate extends SqlUpdate {
    private static final String SQL =
            "delete from `user` where `user_id` = :id";

    public DeleteUserUpdate(DataSource dataSource) {
        super(dataSource, SQL);
        super.declareParameter(new SqlParameter("id", Types.BIGINT));
    }
}
