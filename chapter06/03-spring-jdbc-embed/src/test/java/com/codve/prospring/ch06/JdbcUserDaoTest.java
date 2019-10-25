package com.codve.prospring.ch06;

import com.codve.prospring.ch06.dao.UserDao;
import com.codve.prospring.ch06.entry.Article;
import com.codve.prospring.ch06.entry.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author admin
 * @date 2019/10/25 15:01
 */
public class JdbcUserDaoTest {

    private GenericXmlApplicationContext context;
    private UserDao userDao;

    @Before
    public void setUp() {
        context = new GenericXmlApplicationContext();
        context.load("classpath:h2.xml");
        context.refresh();
        userDao = context.getBean("userDao", UserDao.class);
    }

    @After
    public void tearDown() {
        context.close();
    }

    @Test
    public void testGetNameById() {
        String userName = userDao.getNameById(1L);

        showUsers(userDao.listUsers());

        assertEquals("Jimmy", userName);
    }

    @Test
    public void testUpdate() throws ParseException {
        List<User> users = userDao.listUsers();
        showUsers(users);
        User user = users.get(0);
        user.setName("Joe");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse("2000-09-01");
        user.setBirthday(date);

        userDao.update(user);
        users = userDao.listUsers();
        showUsers(users);
        User updatedUser = users.get(0);
        assertEquals("Joe", updatedUser.getName());

        long diff = Math.abs(date.getTime() - updatedUser.getBirthday().getTime());
        assertTrue(diff < 0.001);

    }

    @Test
    public void testListUsersWithArticle() {
        List<User> users = userDao.listUsersWithArticle();
        showUsers(users);
    }

    private void showUsers(List<User> users) {
        for (User user : users) {
            System.out.println(user);
            List<Article> articles = user.getArticles();
            if (articles != null && articles.size() > 0) {
                for (Article article : articles) {
                    System.out.println("\t" + article.toString());
                }
            }
        }
    }

}