package com.zhang.practice.thread;

import java.util.Random;

/**
 * @ClassName BoundQueueTest
 * @Description:
 * @Author: zhangzh
 * @Date 2019/3/11 10:30
 */
public class BoundQueueTest {

    public static void main(String[] args) {
        BoundQueueTest test = new BoundQueueTest();
        BoundQueue<Integer> queue = new BoundQueue<>(5);

        for (int i = 0; i < 20; i++) {
            Thread t = new Thread (test.new Porducer<>(queue, new Random().nextInt(10) + 1), String.valueOf(i));
            t.start();
        }

        for (int i = 0; i < 20; i++) {
            Thread t = new Thread (test.new Consumer<>(queue), String.valueOf(i));
            t.start();
        }
    }

    class Porducer<T> implements Runnable {

        private BoundQueue<T> boundQueue;

        private T t;

        public Porducer(BoundQueue<T> boundQueue, T t) {
            this.boundQueue = boundQueue;
            this.t = t;
        }

        private void produce() {
            boundQueue.add(t);
        }

        @Override
        public void run() {
            produce();
        }
    }

    class Consumer<T> implements Runnable {

        private BoundQueue<T> boundQueue;

        public Consumer(BoundQueue<T> boundQueue) {
            this.boundQueue = boundQueue;
        }

        private T consumer() {
            return boundQueue.remove();
        }

        @Override
        public void run() {
            consumer();
        }
    }
}
