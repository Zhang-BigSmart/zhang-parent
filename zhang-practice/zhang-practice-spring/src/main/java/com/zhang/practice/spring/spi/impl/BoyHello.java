package com.zhang.practice.spring.spi.impl;

import com.zhang.practice.spring.spi.Hello;

/**
 * @ClassName BoyHello
 * @Description:
 * @Author: zhangzh
 * @Date 2018/10/8 9:20
 */
public class BoyHello implements Hello {

    @Override
    public void sayHello() {
        System.out.println("boy say hello to you");
    }
}
