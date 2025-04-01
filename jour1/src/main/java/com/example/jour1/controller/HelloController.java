package com.example.jour1.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${greeting.message}")
    private String message;

    @GetMapping("/")
    public String helloFromYml() {
        return message;
    }

}
