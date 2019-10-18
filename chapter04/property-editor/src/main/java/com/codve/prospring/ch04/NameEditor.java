package com.codve.prospring.ch04;

import java.beans.PropertyEditorSupport;

/**
 * 自定义的属性编辑器
 */
public class NameEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        String[] name = text.split("\\s"); // 使用空格分隔字符串
        setValue(new FullName(name[0], name[1]));
    }
}
