package com.codve.prospring.ch06.config;

import com.codve.prospring.ch06.dao.UserDao;
import com.codve.prospring.ch06.entry.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author admin
 * @date 2019/10/25 16:24
 */
public class H2ConfigTest {

    private AnnotationConfigApplicationContext context;

    private UserDao userDao;

    @Before
    public void setUp() throws Exception {
        context = new AnnotationConfigApplicationContext(H2Config.class);
        userDao = context.getBean("userDao", UserDao.class);
    }

    @After
    public void tearDown() throws Exception {
        context.close();
    }

    @Test
    public void testGetNameById() {
        String userName = userDao.getNameById(1L);

        showUsers(userDao.listUsers());

        assertEquals("Jimmy", userName);
    }


    private void showUsers(List<User> users) {
        for (User user : users) {
            System.out.println(user);
        }
    }

}