package com.codve.prospring.ch06;

import javax.sql.DataSource;

/**
 * 使用 MappingSqlQuery 完成数据到对象的映射
 * @author admin
 * @date 2019/10/24 17:30
 */
public class ListUsersQuery extends AbstractUserQuery {

    private static final String SQL =
            "select `user_id`, `user_name`, `user_birthday` from `user`";

    public ListUsersQuery(DataSource dataSource) {
        super(dataSource, SQL);
    }
}
