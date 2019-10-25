package com.codve.prospring.ch06;

import com.codve.prospring.ch06.dao.UserDao;
import com.codve.prospring.ch06.entry.User;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

/**

 * @see com.codve.prospring.ch06.CleanUp
 * @author admin
 * @date 2019/10/25 12:26
 */
public class H2Test {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:h2.xml");
        context.refresh();

        UserDao userDao = context.getBean("userDao", UserDao.class);

        String userName = userDao.getNameById(1L);
        System.out.println(userName);

        List<User> users = userDao.listUsers();
        for (User user : users) {
            System.out.println(user.toString());
        }
        context.close();
    }
}
