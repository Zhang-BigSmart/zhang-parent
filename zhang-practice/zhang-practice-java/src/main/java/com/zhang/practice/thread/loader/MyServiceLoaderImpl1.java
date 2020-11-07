package com.zhang.practice.thread.loader;

/**
 * @author : zzh
 * create at:  2019/12/9
 * @description:
 */
public class MyServiceLoaderImpl1 implements IMyServiceLoader {
    @Override
    public String sayHello() {
        return "hello1";
    }

    @Override
    public String getName() {
        return "name1";
    }
}
