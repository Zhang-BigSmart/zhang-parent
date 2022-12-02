package com.zhang.practice.spring.factorybean;

/**
 * <p> TODO
 *
 * @author 哲也
 * @since 2021/6/3
 */
public class MyFactoryBeanService implements IMyFactoryBeanService {



    @Override
    public void doSomeThing() {
        System.out.println("do some thing");
    }
}
