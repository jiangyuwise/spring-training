package com.codve.prospring.ch04;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 创建注解
 * 注解作用范围是 runtime
 * 注解可用于类型和方法上
 * 在方法上使用注解即可创建切入点
 * @see Employee
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface AdviceAnnotation {
}
