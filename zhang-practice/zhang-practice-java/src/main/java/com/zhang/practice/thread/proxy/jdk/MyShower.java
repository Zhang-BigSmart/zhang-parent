package com.zhang.practice.thread.proxy.jdk;

/**
 * @author : zzh
 * create at:  2021/4/5
 * @description:
 */
public class MyShower implements Shower {

    @Override
    public void bathing() {
        System.out.println("I'm bathing in the bathtub...");
    }
}
