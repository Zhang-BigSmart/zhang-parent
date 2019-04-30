package com.zhang.practice.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ConditionDemo
 * @Description:
 * @Author: zhangzh
 * @Date 2019/3/11 9:50
 */
public class ConditionDemo {

    final Lock lock = new ReentrantLock();
    final Condition condition = lock.newCondition();

    public static void main(String[] args) {
        ConditionDemo demo = new ConditionDemo();
        Consumer consumer = demo.new Consumer();
        Producer producer = demo.new Producer();

        consumer.start();
        producer.start();
    }

    class Consumer extends Thread {

        @Override
        public void run() {
            consumer();
        }

        private void consumer() {
            try {
                lock.lock();
                System.out.println("waiting a signal....." + Thread.currentThread().getName());
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(" get a signal....." + Thread.currentThread().getName());
                lock.unlock();
            }
        }
    }

    class Producer extends Thread {
        @Override
        public void run() {
            producer();
        }

        private void producer() {
            try {
                lock.lock();
                System.out.println("get a lock....." + Thread.currentThread().getName());
                condition.signalAll();
                System.out.println("send a signal....." + Thread.currentThread().getName());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
