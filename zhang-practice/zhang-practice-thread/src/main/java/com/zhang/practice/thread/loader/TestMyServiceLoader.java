package com.zhang.practice.thread.loader;

import java.util.ServiceLoader;

/**
 * @author : zzh
 * create at:  2019/12/9
 * @description:
 */
public class TestMyServiceLoader {

    public static void main(String[] argus){
        ServiceLoader<MyServiceLoaderImpl1> serviceLoader = ServiceLoader.load(MyServiceLoaderImpl1.class);
        for (IMyServiceLoader myServiceLoader : serviceLoader){
            System.out.println(myServiceLoader.getName() + myServiceLoader.sayHello());
        }
    }
}
