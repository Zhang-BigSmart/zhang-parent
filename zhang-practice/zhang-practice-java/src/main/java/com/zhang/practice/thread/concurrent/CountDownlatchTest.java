package com.zhang.practice.thread.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @author : zzh
 * create at:  2020/4/30
 * @description:
 */
public class CountDownlatchTest {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(5);
        for(int i = 0; i < 5; i++){
            new Thread(new readNum(i, countDownLatch)).start();
        }
        countDownLatch.await();
        System.out.println("线程执行结束。。。。");
    }

    static class readNum implements Runnable{
        private int id;
        private CountDownLatch latch;
        public readNum(int id,CountDownLatch latch){
            this.id = id;
            this.latch = latch;
        }
        @Override
        public void run() {
            synchronized (this){
                System.out.println("id:"+id);
                latch.countDown();
                System.out.println("线程组任务"+id+"结束，其他任务继续");
            }
        }
    }
}
