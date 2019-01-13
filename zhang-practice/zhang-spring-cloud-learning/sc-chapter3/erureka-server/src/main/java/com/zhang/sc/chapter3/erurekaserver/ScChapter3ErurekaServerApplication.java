package com.zhang.sc.chapter3.erurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ScChapter3ErurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScChapter3ErurekaServerApplication.class, args);
    }

}

