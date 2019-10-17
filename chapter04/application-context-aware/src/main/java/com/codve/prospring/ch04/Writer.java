package com.codve.prospring.ch04;

import org.springframework.context.support.GenericXmlApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;

public class Writer {
    private File file;

    private String filepath;

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    @PostConstruct
    public void postConstruct() throws IOException {
        if (filepath == null) {
            throw new IllegalArgumentException("filepath must be set.");
        }
        file = new File(filepath);
        file.createNewFile();
    }

    @PreDestroy
    public void preDestroy() {
        if (file.delete()) {
            System.out.println("delete file successfully.");
        } else {
            System.err.println("delete file failed.");
        }
    }

    /**
     * 利用已经注册的钩子 ShutdownHock, 这里就不用再调用 context.registerShutdownHock() 了
     */
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:app.xml");
        context.registerShutdownHook();
        context.refresh();

        Writer writer = context.getBean("writer", Writer.class);
    }
}
