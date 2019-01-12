package com.zhang.practice.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = "com.zhang.practice.spring")
public class ZhangPracticeSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZhangPracticeSpringApplication.class, args);
    }
}
