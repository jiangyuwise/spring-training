package com.codve.prospring.ch06.config;

import com.codve.prospring.ch06.dao.UserDao;
import org.junit.After;
import org.junit.Before;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.*;

/**
 * @author admin
 * @date 2019/10/25 16:16
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


}