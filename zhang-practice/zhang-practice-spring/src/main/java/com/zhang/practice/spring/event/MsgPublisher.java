package com.zhang.practice.spring.event;

import org.springframework.beans.BeansException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @author : zzh
 * create at:  2022/7/28
 * @description:
 */
@Service
public class MsgPublisher implements ApplicationContextAware, ApplicationRunner {

    private ApplicationContext applicationContext;

    public static final String default_name = "test";

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @EventListener(condition = "#msgEvent.name=='test'")
    public void consumer(MsgEvent msgEvent) {
        System.out.println("receive msg event" + msgEvent);
    }

    @EventListener(condition = "#msgEvent.name=='test2'")
    public void consumer2(MsgEvent msgEvent) {
        System.out.println("receive2 msg event" + msgEvent);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        applicationContext.publishEvent(new MsgEvent(1, "test2"));
    }
}
