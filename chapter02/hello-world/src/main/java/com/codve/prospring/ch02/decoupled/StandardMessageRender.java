package com.codve.prospring.ch02.decoupled;

/**
 * 配合 xml/HelloWorldSpringDI 食用
 * @see  com.codve.prospring.ch02.xml.HelloWorldSpringDI
 */
public class StandardMessageRender implements MessageRender {
    private MessageProvider messageProvider;

    @Override
    public void render() {
        if (messageProvider == null) {
            throw new RuntimeException("MessageProvider must be set.");
        }
        System.out.println(messageProvider.getMessage());
    }

    @Override
    public void setMessageProvider(MessageProvider provider) {
        this.messageProvider = provider;
    }

    @Override
    public MessageProvider getMessageProvider() {
        return messageProvider;
    }
}
