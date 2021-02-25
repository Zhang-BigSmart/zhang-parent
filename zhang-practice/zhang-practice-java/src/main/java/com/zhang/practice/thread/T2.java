package com.zhang.practice.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : zzh
 * create at:  2020/12/29
 * @description:
 *
 * 写一个多线程，3个线程，第一个线程打印one，第二个打印 Two，第三个打印Three，同时启动三个线程，要求按照 one two three 顺序打印出来
 *
 */
public class T2 {

    public static final int times = 1;

    public static final int mod = 3;

    public static int state = 0;

    public static final Lock lock = new ReentrantLock();

    static class Task implements Runnable {

        private final int num;
        private final String context;

        public Task(int num, String context) {
            this.num = num;
            this.context = context;
        }

        @Override
        public void run() {
            for (int i = 0; i < times;) {
                lock.lock();
                if (state % mod == num) {
                    System.out.println(context);
                    state++;
                    i++;
                }
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {

        Task one = new Task( 0, "one");
        Task two = new Task(1, "two");
        Task three = new Task(2, "three");
        new Thread(one).start();
        new Thread(two).start();
        new Thread(three).start();
        while (true) {
        }
    }
}
