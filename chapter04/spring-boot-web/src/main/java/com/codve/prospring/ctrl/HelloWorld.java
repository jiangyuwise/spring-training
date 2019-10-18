package com.codve.prospring.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
    @RequestMapping("/")
    public String sayHi() {
        return "Hello, world!";
    }
}
