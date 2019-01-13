package com.zhang.sc.chapter3.servicefeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
public class ScChapter3ServiceFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScChapter3ServiceFeignApplication.class, args);
    }

}

