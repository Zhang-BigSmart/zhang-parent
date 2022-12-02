package com.zhang.practice.thread.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author : zzh
 * create at:  2019/12/9
 * @description:
 */
public class SPIMain {

    public static void main(String[] argus){
        ServiceLoader<Executor> serviceLoader = ServiceLoader.load(Executor.class);
        Iterator<Executor> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            Executor executor =  iterator.next();
            System.out.println(executor.invoker());
        }

        String s = "abc";
        System.out.println(s.substring(0, s.length() - 1));
    }
}
