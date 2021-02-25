package com.zhang.practice.thread.ratelimit;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author : zzh
 * create at:  2020/11/26
 * @description:令牌桶限流算法
 *
 * 重点：
 * 1.匀速生产令牌
 * 2.获取令牌
 */
public class TokenBucketRateLimit implements Runnable {

    private int rate;

    private int size;

    private LinkedBlockingQueue<Long> list;

    private ScheduledExecutorService scheduledExecutorService;

    public TokenBucketRateLimit(int rate, int size) {

        list = new LinkedBlockingQueue<>(size);
        long interval = 1000 / rate;
        scheduledExecutorService.scheduleAtFixedRate(this, 0, interval, TimeUnit.MILLISECONDS);
    }

    @Override
    public void run() {
        list.offer(System.currentTimeMillis());
    }

    public void count() {

    }
}
