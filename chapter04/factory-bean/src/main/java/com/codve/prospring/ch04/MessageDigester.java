package com.codve.prospring.ch04;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * MessageDigesterFactory 的容器.
 */
public class MessageDigester {

    private Map<String, MessageDigest> mds = new HashMap<>();

    public void setMds(Map<String, MessageDigest> mds) {
        this.mds = mds;
    }

    public String digest(String alg, String msg) throws RuntimeException {
        Set<String> keys = mds.keySet();
        if (!keys.contains(alg)) {
            throw new RuntimeException("no algorithm for " + alg);
        }
        MessageDigest md = mds.get(alg);
        md.reset();
        md.update(msg.getBytes());
        byte[] result = md.digest();
        return DatatypeConverter.printHexBinary(result);
    }
}
