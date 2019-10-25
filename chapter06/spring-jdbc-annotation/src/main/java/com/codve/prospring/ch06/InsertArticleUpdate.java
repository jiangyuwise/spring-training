package com.codve.prospring.ch06;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.BatchSqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * 批量插入数据
 * @author admin
 * @date 2019/10/25 11:04
 */
public class InsertArticleUpdate extends BatchSqlUpdate {
    private static final String SQL =
            "insert into `article` (`user_id`, `article_title`, `create_time`) " +
                    "values (:userId, :title, :createTime)";

    private static final int BUTCH_SIZE = 10;

    public InsertArticleUpdate(DataSource dataSource) {
        super(dataSource, SQL);

        declareParameter(new SqlParameter("userId", Types.BIGINT));
        declareParameter(new SqlParameter("title", Types.VARCHAR));
        declareParameter(new SqlParameter("createTime", Types.BIGINT));
        setBatchSize(BUTCH_SIZE);
    }
}
