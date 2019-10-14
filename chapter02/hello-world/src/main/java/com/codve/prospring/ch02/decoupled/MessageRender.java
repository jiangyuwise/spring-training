package com.codve.prospring.ch02.decoupled;

public interface MessageRender {
    void render();

    void setMessageProvider(MessageProvider provider);

    MessageProvider getMessageProvider();
}
