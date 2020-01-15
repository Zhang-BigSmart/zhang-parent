package com.zhang.practice.spring;

import com.zhang.practice.spring.autoconf.JobProperties;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author : zzh
 * create at:  2020/1/11
 * @description:
 */
public class AutoConfTest extends BaseTest {


    @Autowired
    private JobProperties jobProperties;

    @Test
    public void test() {
        System.out.println(jobProperties.getToken());
        System.out.println(jobProperties.getExecutor());
    }
}
