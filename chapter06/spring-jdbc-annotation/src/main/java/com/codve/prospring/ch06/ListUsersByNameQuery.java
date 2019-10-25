package com.codve.prospring.ch06;

import org.springframework.jdbc.core.SqlParameter;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * @author admin
 * @date 2019/10/24 18:24
 */
public class ListUsersByNameQuery extends AbstractUserQuery {
    private static final String SQL =
            "select `user_id`, `user_name`, `user_birthday` " +
                    "from `user` where `user_name` like :name";

    public ListUsersByNameQuery(DataSource dataSource) {
        super(dataSource, SQL);
        super.declareParameter(new SqlParameter("name", Types.VARCHAR));
    }
}
