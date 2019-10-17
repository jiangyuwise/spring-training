package com.codve.prospring.ch04;

import org.springframework.context.support.GenericXmlApplicationContext;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;

public class BeanFactoryTest {
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

            // 错误的算法
            result = digester.digest("md6", msg);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // MessageDigest 无法直接获取到, 但可以获取到 MessageDigestFactory, 不推荐这种用法
        MessageDigestFactory md5Factory =
                context.getBean("&md5", MessageDigestFactory.class);
        try {
            MessageDigest md5 = md5Factory.getObject();
            md5.update(msg.getBytes());

            byte[] result = md5.digest();
            String hexResult = DatatypeConverter.printHexBinary(result);
            System.out.println(hexResult);

        } catch (Exception e) {
            e.printStackTrace();
        }
        context.close();
    }
}
