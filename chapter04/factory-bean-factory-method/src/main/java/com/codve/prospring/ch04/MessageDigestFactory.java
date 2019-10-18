package com.codve.prospring.ch04;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MessageDigestFactory {
    private String algName;

    public void setAlgName(String algName) {
        this.algName = algName;
    }

    public MessageDigest getInstance() throws NoSuchAlgorithmException {
        return MessageDigest.getInstance(algName);
    }
}
