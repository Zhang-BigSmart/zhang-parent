package com.zhang.practice.thread;

import java.util.concurrent.CompletableFuture;

/**
 * @author : zzh
 * create at:  2022/4/14
 * @description:
 */
public class CompletableFutureDemo {

    public static void main(String[] args) {
        CompletableFutureDemo demo = new CompletableFutureDemo();

        CompletableFuture completableFuture = new CompletableFuture();

        //

       demo.method1();
    }

    public void method2() {

    }

    public void method1() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(()->{
            System.out.println("compute test");
            return "test";
        });
        String result = future.join();
        System.out.println("get result: " + result);
    }
}
