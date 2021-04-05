package com.zhang.practice.thread.proxy.jdk;

/**
 * @author : zzh
 * create at:  2021/3/5
 * @description:
 */
public class RealSubject implements Subject {

    @Override
    public void doSomething() {
        System.out.println("RealSubject do something");
    }
}
