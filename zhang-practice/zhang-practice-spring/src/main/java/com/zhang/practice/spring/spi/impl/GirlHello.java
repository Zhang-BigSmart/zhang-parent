package com.zhang.practice.spring.spi.impl;

import com.zhang.practice.spring.spi.Hello;

/**
 * @ClassName GirlHello
 * @Description:
 * @Author: zhangzh
 * @Date 2018/10/8 9:21
 */
public class GirlHello implements Hello {

    @Override
    public void sayHello() {
        System.out.println("girl say hello to you");
    }
}
