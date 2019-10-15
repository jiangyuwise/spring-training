package com.codve.prospring.ch03.annotated;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 如果类已经含有 Bean 注解了, 可以直接扫描包
 * 需要配合 HelloWorldMessageProvider, StandardMessageRender 一起使用
 * @see HelloWorldMessageProvider
 * @see StandardMessageRender
 */
@ComponentScan(basePackages = {"com.codve.prospring.ch03.annotated"})
public class HelloWorldConfig2 {
}
