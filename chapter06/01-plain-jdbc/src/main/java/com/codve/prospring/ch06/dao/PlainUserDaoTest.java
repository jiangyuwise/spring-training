package com.codve.prospring.ch06.dao;

import com.codve.prospring.ch06.entry.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author admin
 * @date 2019/10/24 14:12
 */
public class PlainUserDaoTest {
    private static UserDao userDao = new PlainUserDao();
    private static Logger logger = LoggerFactory.getLogger(PlainUserDaoTest.class);

    public static void main(String[] args) {
        listUsers();

        logger.info("insert a new user: ");

        User user = new User();
        user.setName("Jupiter");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse("1992-10-1");
            user.setBirthday(date);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        userDao.insert(user);

        listUsers();

        logger.info("delete a user:");
        userDao.delete(user.getId());

        listUsers();
    }

    private static void listUsers() {
        List<User> users = userDao.listUsers();
        for (User user : users) {
            logger.info(user.toString());
        }
    }
}
