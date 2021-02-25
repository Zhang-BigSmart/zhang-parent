package com.zhang.practice.thread.ratelimit;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : zzh
 * create at:  2020/11/24
 * @description:计数器算法限流
 */
public class CounterLimit {

    public volatile AtomicInteger counter;

    public int limit;

    public int interval;

    public long time;

    public CounterLimit(int limit, int interval) {
        this.counter = new AtomicInteger(0);
        this.time = getCurrentTime();
        this.limit = limit;
        this.interval = interval;
    }

    public boolean addCount() {
        boolean result;
        int count;
        if (time > getCurrentTime() - interval) {
            count = counter.incrementAndGet();
            result = count <= limit;
        } else {
            time = getCurrentTime();
            counter.set(1);
            result = true;
        }
        return result;
    }

    public static long getCurrentTime() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) throws InterruptedException {
        int total = 100;
        CounterLimit counterLimit = new CounterLimit(10, 1000);
        ExecutorService service = Executors.newFixedThreadPool(1000);
        AtomicInteger sCount = new AtomicInteger(0);
        //
        for (int i = 0; i < total; i++) {
            service.execute(() -> {
                if (counterLimit.addCount()) {
                    sCount.addAndGet(1);
                }
            });
        }
        System.out.println("done! total:[" + total + "] success:[" + sCount.get() + "]");

        //
        sCount.set(0);
        for (int i = 0; i < total; i++) {
            service.execute(() -> {
                if (counterLimit.addCount()) {
                    sCount.addAndGet(1);
                }
            });
        }
        System.out.println("done! total:[" + total + "] success:[" + sCount.get() + "]");

        Thread.sleep(2000);
        //
        sCount.set(0);
        for (int i = 0; i < total; i++) {
            service.execute(() -> {
                if (counterLimit.addCount()) {
                    sCount.addAndGet(1);
                }
            });
        }
        System.out.println("done! total:[" + total + "] success:[" + sCount.get() + "]");
    }
}
