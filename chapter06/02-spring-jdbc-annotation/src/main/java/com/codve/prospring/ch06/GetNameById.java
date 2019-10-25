package com.codve.prospring.ch06;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlFunction;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * 使用 SQL 文件组装 SQL 语句
 * @author admin
 * @date 2019/10/24 19:26
 */
public class GetNameById extends SqlFunction {
    private static final String SQL =
            "select getNameById(?)";

    public GetNameById(DataSource dataSource) {
        super(dataSource, SQL);
        declareParameter(new SqlParameter(Types.BIGINT));
        compile();
    }
}
