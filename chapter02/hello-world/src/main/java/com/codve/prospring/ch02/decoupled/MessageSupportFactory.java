package com.codve.prospring.ch02.decoupled;

import java.io.IOException;
import java.util.Properties;

public class MessageSupportFactory {
    private static MessageSupportFactory instance;

    private Properties properties;
    private MessageRender render;
    private MessageProvider provider;

    static {
        try {
            instance = new MessageSupportFactory();
        } catch (IOException
                | ClassNotFoundException
                | IllegalAccessException
                | InstantiationException e) {
            System.out.println(e.getMessage());
        }
    }

    public MessageSupportFactory() throws IOException,
            ClassNotFoundException, IllegalAccessException, InstantiationException {
        properties = new Properties();

        properties.load(getClass().getResourceAsStream("/msf.properties"));
        String renderClass = properties.getProperty("render.class");
        String providerClass = properties.getProperty("provider.class");

        render = (MessageRender) Class.forName(renderClass).newInstance();
        provider = (MessageProvider) Class.forName(providerClass).newInstance();

        render.setMessageProvider(provider);
    }

    public static MessageSupportFactory getInstance() {
        return instance;
    }

    public MessageRender getRender() {
        return render;
    }

    public MessageProvider getProvider() {
        return provider;
    }
}
