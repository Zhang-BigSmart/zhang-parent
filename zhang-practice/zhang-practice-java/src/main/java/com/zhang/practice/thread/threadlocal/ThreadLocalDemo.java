package com.zhang.practice.thread.threadlocal;

/**
 * <p> TODO
 *
 * @author 哲也
 * @since 2021/7/14
 */
public class ThreadLocalDemo {

    public static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static final ThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<String>();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("thread:" + Thread.currentThread() + "start!");
        threadLocal.set("key");
        inheritableThreadLocal.set("value");

        Thread thread = new Thread(() -> {
            System.out.println("thread:" + Thread.currentThread() + "start!");
            System.out.println(threadLocal.get());
            System.out.println(inheritableThreadLocal.get());
            System.out.println("thread:" + Thread.currentThread() + "end!");
        });
        thread.start();

        Thread.sleep(2000);
        System.out.println("thread:" + Thread.currentThread() + "end!");
    }

}
