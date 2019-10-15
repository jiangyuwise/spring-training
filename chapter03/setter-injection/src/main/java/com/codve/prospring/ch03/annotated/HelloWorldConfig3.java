package com.codve.prospring.ch03.annotated;

import org.springframework.context.annotation.ImportResource;

/**
 * 混合使用 java 注解和 XML 配置文件
 * 注意 xml 中定义的 bean 是没有使用任何注解的 java 原始类.
 * 这主要用于兼容老项目
 */
@ImportResource(locations={"classpath:common/app.xml"})
public class HelloWorldConfig3 {
}
