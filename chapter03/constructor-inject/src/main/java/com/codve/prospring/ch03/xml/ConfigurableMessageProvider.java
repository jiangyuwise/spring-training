package com.codve.prospring.ch03.xml;

import com.codve.prospring.ch02.decoupled.MessageProvider;

/**
 * 使用构造函数 和 xml 配置文件完成注入
 * @see ConfigurableMessageProviderTest
 */
public class ConfigurableMessageProvider implements MessageProvider {
    private String message;

    public ConfigurableMessageProvider(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
