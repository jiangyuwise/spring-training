package com.codve.prospring.ch04;

import org.springframework.context.support.GenericXmlApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;

/**
 * 使用 @PreDestroy 注解指定 bean 销毁前要执行的方法
 * app.xml 中需要使用 <context:annotation-config/>
 */
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

    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:app.xml");
        context.refresh();

        Writer writer = context.getBean("writer", Writer.class);

        context.close();
    }
}
