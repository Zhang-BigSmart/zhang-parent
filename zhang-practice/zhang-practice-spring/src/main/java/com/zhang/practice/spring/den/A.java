package com.zhang.practice.spring.den;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * @author : zzh
 * create at:  2020/5/2
 * @description:
 */
@Slf4j
@Service
public class A implements BeanPostProcessor, InitializingBean, SmartInitializingSingleton {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof B) {
            System.out.println("yes");
        }
        log.info("===postProcessBeforeInitialization===");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info("===postProcessAfterInitialization===");
        return bean;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("===afterPropertiesSet===");
    }

    @Override
    public void afterSingletonsInstantiated() {
        log.info("===afterSingletonsInstantiated===");
    }
}
