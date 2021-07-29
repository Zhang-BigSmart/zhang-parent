package com.zhang.practice.spring.factorybean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * <p> TODO
 *
 * @author 哲也
 * @since 2021/6/3
 */
public class MyFactoryBean implements FactoryBean<IMyFactoryBeanService>, InitializingBean, DisposableBean {

    @Override
    public void destroy() throws Exception {

    }

    @Override
    public IMyFactoryBeanService getObject() throws Exception {
        System.out.println("=== getObject ===");
        return new MyFactoryBeanService();
    }

    @Override
    public Class<?> getObjectType() {
        System.out.println("=== getObjectType ===");
        return IMyFactoryBeanService.class;
    }

    @Override
    public boolean isSingleton() {
        System.out.println("=== isSingleton ===");
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 比 FactoryBean 的方法要早
        System.out.println("=== afterPropertiesSet ===");
    }
}
