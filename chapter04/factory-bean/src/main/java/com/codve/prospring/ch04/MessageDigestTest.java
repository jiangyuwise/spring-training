package com.codve.prospring.ch04;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * md 测试
 */
public class MessageDigestTest {
    public static void main(String[] args) {
        String msg = "hello, world";

        try {
            MessageDigest md = MessageDigest.getInstance("md5"); // 获取算法
            md.update(msg.getBytes()); // 传入参数
            byte[] digest = md.digest(); // 获取摘要
            String hexResult = DatatypeConverter.printHexBinary(digest);
            System.out.println(hexResult);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
