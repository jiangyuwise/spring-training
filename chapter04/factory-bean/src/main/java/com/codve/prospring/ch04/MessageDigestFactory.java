package com.codve.prospring.ch04;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.security.MessageDigest;

/**
 * 对于无法直接使用 new 创建的类, 通常需要实现 FactoryBean 接口
 */
public class MessageDigestFactory implements FactoryBean<MessageDigest>, InitializingBean {

    private String algorithmName = "md5";

    private MessageDigest messageDigest;

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    @Override
    public MessageDigest getObject() throws Exception {
        return messageDigest;
    }

    @Override
    public Class<MessageDigest> getObjectType() {
        return MessageDigest.class;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        messageDigest = MessageDigest.getInstance(algorithmName);
    }

    public boolean isSingleton() {
        return true;
    }
}
