package com.codve.prospring.ch04;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.io.File;

/**
 * 通过继承 Spring 的 DisposableBean 指定 bean 在销毁前要执行的方法
 * app.xml 中无需指定 destroy-method
 */
public class Writer implements InitializingBean, DisposableBean {
    private File file;

    private String filepath;

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (filepath == null) {
            throw new IllegalArgumentException("filepath must be set.");
        }
        file = new File(filepath);
        file.createNewFile();
    }

    @Override
    public void destroy() throws Exception {
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
