package com.codve.prospring.ch04;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

/**
 * 使用注解配置代替 app.xml
 */
public class MessageDigestConfigTest {

    @Configuration
    static class MessageDigestConfig {
        @Bean
        public MessageDigestFactory md5Factory() {
            MessageDigestFactory md5Factory = new MessageDigestFactory();
            md5Factory.setAlgorithmName("md5");
            return md5Factory;
        }

        @Bean
        public MessageDigestFactory sha1Factory() {
            MessageDigestFactory sha1Factory = new MessageDigestFactory();
            sha1Factory.setAlgorithmName("sha1");
            return sha1Factory;
        }

        @Bean
        MessageDigester digester() throws Exception {
            MessageDigester digester = new MessageDigester();

            Map<String, MessageDigest> mds = new HashMap<>();
            mds.put("md5", md5Factory().getObject());
            mds.put("sha1", sha1Factory().getObject());

            digester.setMds(mds);
            return digester;
        }
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MessageDigestConfig.class);

        MessageDigester digester = context.getBean("digester", MessageDigester.class);

        try {
            String result = digester.digest("md5", "hello, world");
            System.out.println(result);

            result = digester.digest("sha1", "hello, world");
            System.out.println(result);

            // 错误的算法
            result = digester.digest("md6", "hello, world");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        context.close();
    }
}
