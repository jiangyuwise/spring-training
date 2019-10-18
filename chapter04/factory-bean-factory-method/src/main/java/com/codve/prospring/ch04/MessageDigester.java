package com.codve.prospring.ch04;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 在 app.XML 文件中, 用 factory-bean 指定factory, 用 factory-method 指定获取实例的方法
 */
public class MessageDigester {
    private Map<String, MessageDigest> mds = new HashMap<>();

    public void setMds(Map<String, MessageDigest> mds) {
        this.mds = mds;
    }

    public String digest(String alg, String msg) throws NoSuchAlgorithmException {
        Set<String> keys = mds.keySet();
        if (!keys.contains(alg)) {
            throw new NoSuchAlgorithmException("No algorithm for " + alg);
        }
        MessageDigest md = mds.get(alg);
        md.update(msg.getBytes());
        byte[] result = md.digest();
        return DatatypeConverter.printHexBinary(result);
    }
}
