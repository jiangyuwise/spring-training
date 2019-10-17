package com.codve.prospring.ch04;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.io.File;

/**
 * 在 app.xml 使用 destroy-method 指定 bean 销毁前要执行的方法
 * context.close() 会清理所有的 bean.
 */
public class Writer implements InitializingBean {

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

    public void destroy() {
        if (file.delete()) {
            System.out.println("delete file successfully.");
        } else {
            System.out.println("delete file failed.");
        }
    }

    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:app.xml");
        context.refresh();

        Writer writer = context.getBean("writer", Writer.class);

        /**
         * context.close() 会自动调用所有 bean 的 destroy-method.
         * 也可以用 context.registerShutdownHock() 替换 context.close();
         */
        context.close();
//        context.registerShutdownHook();

    }
}
