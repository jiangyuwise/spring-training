package com.codve.prospring.ch06.dao;

import com.codve.prospring.ch06.*;
import com.codve.prospring.ch06.entry.Article;
import com.codve.prospring.ch06.entry.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.*;

/**
 * 实现 UserDao 接口, 实际上是使用委托的适配器模式
 * 与接口相比, 引入了 DataSource实例
 *
 * 使用 @Resource 注入 dataSource.
 * @author admin
 * @date 2019/10/24 17:27
 */
@Repository("userDao")
public class JdbcUserDao implements UserDao{

    private DataSource dataSource;

    private ListUsersQuery listUsersQuery;

    private ListUsersByNameQuery listUsersByNameQuery;

    private InsertUserUpdate insertUserUpdate;

    private UpdateUserUpdate updateUserUpdate;

    private DeleteUserUpdate deleteUserUpdate;

    private GetNameById getNameById;

    private InsertArticleUpdate insertArticleUpdate;

    @Override
    public List<User> listUsers() {
        return listUsersQuery.execute();
    }

    //TODO 有错
    @Override
    public String getNameById(Long id) {
        List<String> result = getNameById.execute(id);
        return result.get(0);
    }

    @Override
    public List<User> listUsersByName(String name) {
        Map<String, Object> params = new HashMap<>(16);
        params.put("name", name);
        return listUsersByNameQuery.executeByNamedParam(params);
    }

    @Override
    public void insert(User user) {
        Map<String, Object> params = new HashMap<>(16);
        params.put("name", user.getName());
        params.put("birthday", user.getBirthday().getTime());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        insertUserUpdate.updateByNamedParam(params, keyHolder);

        user.setId(keyHolder.getKey().longValue());
    }

    @Override
    public void update(User user) {
        Map<String, Object> params = new HashMap<>(16);
        params.put("name", user.getName());
        params.put("birthday", user.getBirthday().getTime());
        params.put("id", user.getId());
        updateUserUpdate.updateByNamedParam(params);
    }

    @Override
    public void delete(Long id) {
        Map<String, Object> paramMap = new HashMap<>(2);
        paramMap.put("id", id);
        deleteUserUpdate.updateByNamedParam(paramMap);
    }

    @Override
    public List<User> listUsersWithArticle() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        String sql = "select `user`.`user_id`, `user`.`user_name`, `user`.`user_birthday`, " +
                "`article`.`article_id`, `article`.`article_title`, `article`.`create_time` " +
                "from `user` left join `article` on `user`.`user_id` = `article`.`user_id`";
        return jdbcTemplate.query(sql, rs -> {
            Map<Long, User> userMap = new HashMap<>();
            User user;

            while (rs.next()) {
                Long userId = rs.getLong("user_id");
                user = userMap.get(userId);
                if (user == null) {
                    user = new User();
                    user.setId(userId);
                    user.setName(rs.getString("user_name"));
                    user.setBirthday(new Date(rs.getLong("user_birthday")));
                    user.setArticles(new ArrayList<>());
                    userMap.put(userId, user);
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
            return new ArrayList<>(userMap.values());
        });
    }

    @Override
    public void insertWithArticle(User user) {
        insertArticleUpdate = new InsertArticleUpdate(dataSource);
        Map<String, Object> params = new HashMap<>(16);
        params.put("name", user.getName());
        params.put("birthday", user.getBirthday().getTime());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        insertUserUpdate.updateByNamedParam(params, keyHolder);
        user.setId(keyHolder.getKey().longValue());

        List<Article> articles = user.getArticles();
        if (articles != null && articles.size() > 0) {
            for (Article article : articles) {
                params = new HashMap<>(16);
                params.put("userId", user.getId());
                params.put("title", article.getTitle());
                params.put("createTime", article.getCreateTime().getTime());
                insertArticleUpdate.updateByNamedParam(params);
            }
        }
        insertArticleUpdate.flush();
    }

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.listUsersQuery = new ListUsersQuery(dataSource);
        this.listUsersByNameQuery = new ListUsersByNameQuery(dataSource);
        this.insertUserUpdate = new InsertUserUpdate(dataSource);
        this.updateUserUpdate = new UpdateUserUpdate(dataSource);
        this.deleteUserUpdate = new DeleteUserUpdate(dataSource);
        this.getNameById = new GetNameById(dataSource);

    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
