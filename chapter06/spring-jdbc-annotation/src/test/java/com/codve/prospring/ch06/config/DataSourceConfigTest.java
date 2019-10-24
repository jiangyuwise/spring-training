package com.codve.prospring.ch06.config;


import com.codve.prospring.ch06.dao.UserDao;
import com.codve.prospring.ch06.entry.Article;
import com.codve.prospring.ch06.entry.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author admin
 * @date 2019/10/24 17:51
 */
public class DataSourceConfigTest {

    private AnnotationConfigApplicationContext context;

    private UserDao userDao;

    @Before
    public void setUp() {
        context = new AnnotationConfigApplicationContext(DataSourceConfig.class);
        userDao = context.getBean("userDao", UserDao.class);
    }

    @After
    public void tearsDown() {
        context.close();
    }

    // 测试查找所有用户
    @Test
    public void listUsersTest() {
        List<User> users = userDao.listUsers();
        assertEquals(3, users.size());

        showUsers(users);
    }

    // 测试按名字模糊搜索用户
    @Test
    public void listUsersByName() {
        List<User> users = userDao.listUsersByName("%j%");
        assertNotNull(users);
        showUsers(users);
    }

    // 插入用户
    @Test
    public void insert() throws ParseException {
        User user = new User();
        user.setName("admin");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse("1992-12-21");
        user.setBirthday(date);

        userDao.insert(user);
        assertNotNull(user.getId());

        List<User> users = userDao.listUsers();
        showUsers(users);
    }

    // 更新用户
    @Test
    public void update() throws ParseException {
        User user = new User();
        user.setName("Alice");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse("1998-8-1");
        user.setBirthday(date);
        user.setId(2L);

        userDao.update(user);

        showUsers(userDao.listUsers());
    }

    // 删除用户
    @Test
    public void delete() {
        userDao.delete(10L);
        showUsers(userDao.listUsers());
    }

    // 根据 id 查找用户名
    @Test
    @Ignore("有毒, 别测试")
    public void getNameById() {
        String name = userDao.getNameById(1L);
        assertNotNull(name);
    }

    // 左联查找用户和文章
    @Test
    public void listUsersWithArticle() {
        showUsers(userDao.listUsersWithArticle());
    }

    private void showUsers(List<User> users) {
        users.stream().forEach(user -> {
            System.out.println(user);
            if (user.getArticles() != null) {
                for (Article article : user.getArticles()) {
                    System.out.println("\t " + article);
                }
            }
        });
    }

}