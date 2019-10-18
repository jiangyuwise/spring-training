package com.codve.prospring.ch04;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.security.NoSuchAlgorithmException;

public class MessageDigestTest {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:app.xml");
        context.refresh();

        MessageDigester digester = context.getBean("digester", MessageDigester.class);
        String msg = "hello, world";
        try {
            String result = digester.digest("md5", msg);
            System.out.println(result);

            result = digester.digest("sha1", msg);
            System.out.println(result);

            // 错误的签名算法
            result = digester.digest("md6", msg);
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }

        context.close();
    }
}
