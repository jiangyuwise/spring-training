package com.codve.prospring.ch02.decoupled;

public class HelloWorldDecoupledWithFactory {
    public static void main(String[] args) {
        MessageSupportFactory instance = MessageSupportFactory.getInstance();
        MessageRender render = instance.getRender();
        MessageProvider provider = instance.getProvider();
        render.setMessageProvider(provider);
        render.render();

    }
}
