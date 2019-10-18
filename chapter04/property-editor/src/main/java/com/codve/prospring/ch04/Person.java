package com.codve.prospring.ch04;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * 定义一个 person 类, 有个属性 FullName, 使用自定义的属性编辑器将字符串转换为 FullName 对象
 * @see NameEditor, app-name-editor.xml
 */
public class Person {
    private FullName name;

    public FullName getName() {
        return name;
    }

    public void setName(FullName name) {
        this.name = name;
    }

    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:app-name-editor.xml");
        context.refresh();

        Person person = context.getBean("person", Person.class);
        System.out.println(person.getName());

        context.close();
    }
}
