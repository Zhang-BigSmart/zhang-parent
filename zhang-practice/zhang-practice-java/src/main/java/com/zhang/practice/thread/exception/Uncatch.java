package com.zhang.practice.thread.exception;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author : zzh
 * create at:  2020/2/17
 * @description:
 */
public class Uncatch {

    public void demo1() {
        ExecutorService service = Executors.newFixedThreadPool(1);
        Thread thread = new Thread(() -> {
            throw new RuntimeException();
        });
        service.execute(thread);
    }


    public void demo2() {
        ExecutorService service = Executors.newFixedThreadPool(1);
        Thread thread = new Thread(() -> {
            try {
                throw new RuntimeException();
                } catch (Exception e) {
                    System.out.println("thread-demo2");
                    e.printStackTrace();
                }
        });
        service.execute(thread);
    }

    public void demo3() {
        ExecutorService service = Executors.newFixedThreadPool(1);
        Thread thread = new Thread(() -> {
            throw new RuntimeException();
        });
        thread.setUncaughtExceptionHandler((t, e) -> {
            System.out.println("thread-demo3");
            e.printStackTrace();
        });
        service.execute(thread);
    }

    public void demo4() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setUncaughtExceptionHandler((t, e) -> {
                    System.out.println("thread-demo4");
                    e.printStackTrace();
                })
                .build();
        ExecutorService service = Executors.newSingleThreadExecutor(threadFactory);
        Thread thread = new Thread(() -> {
            throw new RuntimeException();
        });
        service.execute(thread);
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Uncatch a = new Uncatch();
        a.demo4();
        System.out.println("main end");
    }
}
