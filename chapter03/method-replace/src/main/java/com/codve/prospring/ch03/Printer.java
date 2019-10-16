package com.codve.prospring.ch03;

/**
 * 方法替换, Printer 的方法会被动态替换
 * XML 中只替换第一个方法
 */
public class Printer {

    public String print(String msg) {
        return "* " + msg +  " *";
    }

    public String print(Object obj) {
        return "| " + obj.toString() + " |";
    }
}
