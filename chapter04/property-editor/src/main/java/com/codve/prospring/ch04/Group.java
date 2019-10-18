package com.codve.prospring.ch04;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 使用内置的 propertyEditor
 * propertyEditor 用于将字符串转换为合适的对象, 如将字符串"2019-10-18" 转换为 new Date("2019-10-18")
 * 下面定义了一个群 group, 有2个属性
 * Date 表示创建时间, 使用spring 内置的的属性编辑器
 * names 表示成员名单, 使用 Spring 内置的属性编辑器
 */
public class Group {
    private Date date; // 使用内置的 CustomDateEditor
    private List<String> names; // 使用内置的 CustomCollectionEditor

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    // dateEditor 需要注册
    public static class DateEditorRegistrar implements PropertyEditorRegistrar {
        @Override
        public void registerCustomEditors(PropertyEditorRegistry registry) {
            SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
            registry.registerCustomEditor(Date.class,
                    new CustomDateEditor(format, true));
            registry.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        }
    }

    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:app.xml");
        context.refresh();

        Group group = context.getBean("group", Group.class);
        System.out.println(group.getDate());

        // 获取 names
        List<String> names = group.getNames();
        for (String name : names) {
            System.out.println(name);
        }

        context.close();
    }
}
