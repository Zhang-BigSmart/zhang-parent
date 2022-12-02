package com.zhang.practice.thread;

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : zzh
 * create at:  2020/12/29
 * @description:
 *
 * 写一个多线程，3个线程，第一个线程打印one，第二个打印 Two，第三个打印Three，同时启动三个线程，要求按照 one two three 顺序打印出来
 *
 */
public class T {

    public static final Object object = new Object();

    static class Task implements Runnable {
        private boolean flag;
        private final Task preTask;
        private final String context;

        public Task(Task preTask, String context) {
            this.preTask = preTask;
            this.context = context;
        }

        @Override
        public void run() {
            synchronized (object) {
                if (preTask != null && !preTask.flag) {
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(context);
                flag = true;
                object.notifyAll();
            }
        }
    }

    public static void main(String[] args) {

        Task one = new Task( null, "one");
        Task two = new Task(one, "two");
        Task three = new Task(two, "three");
        new Thread(one).start();
        new Thread(two).start();
        new Thread(three).start();
        while (true) {
        }
    }
}
