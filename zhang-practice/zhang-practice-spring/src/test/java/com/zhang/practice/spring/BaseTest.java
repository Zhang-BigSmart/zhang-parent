package com.zhang.practice.spring;

import com.zhang.practice.spring.den.A;
import com.zhang.practice.spring.den.B;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseTest {

    @Autowired
    private A a;

    @Autowired
    private B b;

    @Test
    public void contextLoads() {
        //a.logA();
        b.logB();
    }



}
