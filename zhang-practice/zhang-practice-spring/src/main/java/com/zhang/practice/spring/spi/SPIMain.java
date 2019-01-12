package com.zhang.practice.spring.spi;

import java.util.ServiceLoader;

/**
 * @ClassName SPIMain
 * @Description:
 * @Author: zhangzh
 * @Date 2018/10/8 9:23
 */
public class SPIMain {

    public static void main(String[] args) {

        ServiceLoader<Hello> loaders = ServiceLoader.load(Hello.class);
        for (Hello in : loaders) {
            in.sayHello();
        }
    }
}
