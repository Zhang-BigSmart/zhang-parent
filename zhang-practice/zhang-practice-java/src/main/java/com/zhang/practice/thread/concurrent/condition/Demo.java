package com.zhang.practice.thread.concurrent.condition;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : zzh
 * create at:  2020/5/14
 * @description:
 */
public class Demo {

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void methodAwait() {
        try {
            lock.lock();
            System.out.println(String.format("### 当前线程:%s waiting ###", Thread.currentThread().getName()));
            condition.await();
            System.out.println(String.format("### 当前线程:%s finished ###", Thread.currentThread().getName()));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void methodSignal() {
        try {
            lock.lock();
            System.out.println(String.format("### 当前线程:%s signal ###", Thread.currentThread().getName()));
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        /*Demo demo = new Demo();
        Thread t1 = new Thread(() -> demo.methodAwait(), "thread-A");
        Thread t2 = new Thread(() -> demo.methodAwait(), "thread-B");
        Thread t3 = new Thread(() -> demo.methodAwait(), "thread-C");
        Thread t4 = new Thread(() -> demo.methodSignal(), "thread-D");
        t1.start();
        t2.start();
        t3.start();
        Thread.sleep(2000);
        t4.start();*/
        System.out.println(System.currentTimeMillis());
        System.out.println(23 % 10);
        System.out.println(23 / 10);

        int i = 0;
        while (i++ < 10) {
            System.out.println(Math.abs(new Random(System.currentTimeMillis()).nextInt() % 99999999));
        }
    }
}
