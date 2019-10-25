package com.codve.prospring.ch06;

import com.codve.prospring.ch06.dao.UserDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author admin
 * @date 2019/10/25 17:46
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                SpringApplication.run(Application.class);
        UserDao userDao = context.getBean("userDao", UserDao.class);
        String username = userDao.getNameById(1L);
        System.out.println(username);
    }
}
