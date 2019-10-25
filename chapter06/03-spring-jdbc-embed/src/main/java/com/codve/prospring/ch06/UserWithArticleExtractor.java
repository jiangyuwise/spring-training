package com.codve.prospring.ch06;

import com.codve.prospring.ch06.entry.Article;
import com.codve.prospring.ch06.entry.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * 使用 ResultSetExtractor 检索联表查询的结果
 *
 * @author admin
 * @date 2019/10/25 16:57
 */
public class UserWithArticleExtractor implements ResultSetExtractor<List<User>> {
    @Override
    public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Long, User> users = new HashMap<>();
        User user;
        while (rs.next()) {
            Long userId = rs.getLong("user_id");
            user = users.get(userId);
            if (user == null) {
                user = new User();
                user.setId(rs.getLong("user_id"));
                user.setName(rs.getString("user_name"));
                user.setBirthday(new Date(rs.getLong("user_birthday")));
                user.setArticles(new ArrayList<>());
                users.put(userId, user);
            }
            Long articleId = rs.getLong("article_id");
            if (articleId > 0) {
                Article article = new Article();
                article.setId(articleId);
                article.setUserId(userId);
                article.setTitle(rs.getString("article_title"));
                article.setCreateTime(new Date(rs.getLong("create_time")));
                user.addArticle(article);
            }
        }
        return new ArrayList<>(users.values());
    }
}
