package com.example.appkubernetesdemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
@RequiredArgsConstructor
public class HelloController {

    @GetMapping
    public String sayHello() {

        return "Hello World!";
    }

    @GetMapping("/salam")
    public String sayHello2() {
        return "Salam!";
    }


    @GetMapping("/hii")
    public String sayHello3() {
        return "Hiiiia!";
    }


    @GetMapping("/ss")
    public String sayHello4() {
        return "ssss!";
    }
}
