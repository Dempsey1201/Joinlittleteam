package com.example.javaee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example.javaee.controller","com.example.javaee.service","com.example.javaee.mapper","com.example.javaee.entity"})

public class JavaeeApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaeeApplication.class, args);
    }

}
