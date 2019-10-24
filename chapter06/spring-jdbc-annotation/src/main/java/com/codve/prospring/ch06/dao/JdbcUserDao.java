package com.codve.prospring.ch06.dao;

import com.codve.prospring.ch06.*;
import com.codve.prospring.ch06.entry.Article;
import com.codve.prospring.ch06.entry.User;
import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 实现 UserDao 接口, 实际上是使用委托的适配器模式
 * 使用 @Resource 注入 dataSource.
 * @author admin
 * @date 2019/10/24 17:27
 */
@Repository("userDao")
public class JdbcUserDao implements UserDao{

    private static Logger logger = LoggerFactory.getLogger(JdbcUserDao.class);

    private DataSource dataSource;

    private ListUsers listUsers;

    private ListUsersByName listUsersByName;

    private InsertUser insertUser;

    private UpdateUser updateUser;

    private DeleteUser deleteUser;

    private GetNameById getNameById;

    @Override
    public List<User> listUsers() {
        return listUsers.execute();
    }

    //TODO 有错
    @Override
    public String getNameById(Long id) {
        List<String> result = getNameById.execute(id);
        return result.get(0);
    }

    @Override
    public List<User> listUsersByName(String name) {
        Map<String, Object> paramMap = new HashMap<>(1);
        paramMap.put("name", name);
        return listUsersByName.executeByNamedParam(paramMap);
    }

    @Override
    public void insert(User user) {
        Map<String, Object> paramMap = new HashMap<>(2);
        paramMap.put("name", user.getName());
        paramMap.put("birthday", user.getBirthday().getTime());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        insertUser.updateByNamedParam(paramMap, keyHolder);

        user.setId(keyHolder.getKey().longValue());
    }

    @Override
    public void update(User user) {
        Map<String, Object> paramMap = new HashMap<>(4);
        paramMap.put("name", user.getName());
        paramMap.put("birthday", user.getBirthday().getTime());
        paramMap.put("id", user.getId());
        updateUser.updateByNamedParam(paramMap);
    }

    @Override
    public void delete(Long id) {
        Map<String, Object> paramMap = new HashMap<>(2);
        paramMap.put("id", id);
        deleteUser.updateByNamedParam(paramMap);
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

    }

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.listUsers = new ListUsers(dataSource);
        this.listUsersByName = new ListUsersByName(dataSource);
        this.insertUser = new InsertUser(dataSource);
        this.updateUser = new UpdateUser(dataSource);
        this.deleteUser = new DeleteUser(dataSource);
        this.getNameById = new GetNameById(dataSource);

    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
