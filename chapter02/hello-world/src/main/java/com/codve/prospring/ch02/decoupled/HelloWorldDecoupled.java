package com.codve.prospring.ch02.decoupled;

public class HelloWorldDecoupled {
    public static void main(String[] args) {
        MessageRender render = new StandardMessageRender();
        MessageProvider provider = new HelloWorldMessageProvider();
        render.setMessageProvider(provider);
        render.render();

    }
}
