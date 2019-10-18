package com.codve.prospring.ch04;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;

public class ResourceTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext();
        // 访问文件资源
        try {
            File file = File.createTempFile("test", ".txt");
            Resource res1 = context.getResource("file://" + file.getPath());
            System.out.println(file.getAbsolutePath());
            file.deleteOnExit();

            // classpath: 是 Spring 特有的协议, 表示类的路径
            Resource res2 = context.getResource("classpath:test.txt");

            Resource res3 = context.getResource("http://www.baidu.com");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }




    }
}
