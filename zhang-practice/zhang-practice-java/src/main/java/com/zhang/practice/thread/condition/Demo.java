package com.zhang.practice.thread.condition;

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
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();

    public void method1() {
        try {
            lock.lock();
            System.out.println(String.format("### 当前线程:%s waiting ###", Thread.currentThread().getName()));
            condition1.await();
            System.out.println(String.format("### 当前线程:%s finished ###", Thread.currentThread().getName()));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void method2() {
        try {
            lock.lock();
            System.out.println(String.format("### 当前线程:%s waiting ###", Thread.currentThread().getName()));
            condition1.await();
            System.out.println(String.format("### 当前线程:%s finished ###", Thread.currentThread().getName()));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void method3() {
        try {
            lock.lock();
            System.out.println(String.format("### 当前线程:%s waiting ###", Thread.currentThread().getName()));
            condition2.await();
            System.out.println(String.format("### 当前线程:%s finished ###", Thread.currentThread().getName()));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void method4() {
        try {
            lock.lock();
            System.out.println(String.format("### 当前线程:%s signal ###", Thread.currentThread().getName()));
            condition1.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void method5() {
        try {
            lock.lock();
            System.out.println(String.format("### 当前线程:%s signal ###", Thread.currentThread().getName()));
            condition2.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Demo demo = new Demo();

        Thread t1 = new Thread(() -> demo.method1(),"t1");

        Thread t2 = new Thread(() -> demo.method2(), "t2");

        Thread t3 = new Thread(() -> demo.method3(), "t3");

        Thread t4 = new Thread(() -> demo.method4(), "t4");

        Thread t5 = new Thread(() -> demo.method5(), "t5");

        t1.start();
        t2.start();
        t3.start();

        Thread.sleep(2000);

        t4.start();

        Thread.sleep(5000);

        t5.start();

    }




}
