package com.codve.prospring.ch03.annotated;

import com.codve.prospring.ch02.decoupled.MessageProvider;
import org.springframework.stereotype.Component;

/**
 * 创建一个普通的 bean, name = "provider"
 * 需要配合 StandardMessageRender, DeclaredSpringComponents, annotated/app.xml 食用
 * @see StandardMessageRender
 * @see com.codve.prospring.ch03.DeclaredSpringComponents
 */
@Component("provider")
public class HelloWorldMessageProvider implements MessageProvider{
    @Override
    public String getMessage() {
        return "hello, world";
    }
}
