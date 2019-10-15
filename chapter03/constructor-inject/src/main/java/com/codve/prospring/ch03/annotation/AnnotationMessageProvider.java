package com.codve.prospring.ch03.annotation;

import com.codve.prospring.ch02.decoupled.MessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 使用构造函数和 java 注解完成注入, 同样需要 xml 的支持
 * @see AnnotationMessageProviderTest
 */
@Service("provider")
public class AnnotationMessageProvider implements MessageProvider {
    private String message;

    @Autowired
    public AnnotationMessageProvider(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
