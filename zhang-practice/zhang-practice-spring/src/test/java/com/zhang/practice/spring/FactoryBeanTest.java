package com.zhang.practice.spring;

import com.zhang.practice.spring.factorybean.IMyFactoryBeanService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * <p> TODO
 *
 * @author 哲也
 * @since 2021/6/3
 */
public class FactoryBeanTest extends BaseTest{

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void test() {
        IMyFactoryBeanService bean = applicationContext.getBean(IMyFactoryBeanService.class);
        bean.doSomeThing();
    }
}
