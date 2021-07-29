package com.zhang.practice.spring.factorybean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p> TODO
 *
 * @author 哲也
 * @since 2021/6/3
 */
@Configuration
public class MyFactoryBeanConf {

    @Bean
    public MyFactoryBean myFactoryBean() {
        return new MyFactoryBean();
    }
}
