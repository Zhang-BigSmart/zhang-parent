package com.zhang.practice.thread.ratelimit;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : zzh
 * create at:  2020/11/25
 * @description:滑动窗口算法限流
 */
@Slf4j
public class SlideWindowRateLimit2 {

    /**
     * 限流大小
     */
    private int limit;
    /**
     * 窗口大小(单位：毫秒)
     */
    private int windowSize;
    /**
     * 每个小窗口时间间隔大小
     */
    private long windowPeriod;
    /**
     * 小窗口数
     */
    private int windowNum;
    /**
     * 每个窗口的计数器
     */
    private AtomicInteger[] counters;
    /**
     * 当前窗口的索引
     */
    private int currIndex;

    private long reqTime;

    private Lock lock = new ReentrantLock();

    public SlideWindowRateLimit2(int limit, int windowSize, int windowNum) {
        this.limit = limit;
        this.windowNum = windowNum;
        this.windowSize = windowSize;
        this.windowPeriod = windowSize / windowNum;
        this.currIndex = 0;
        this.reqTime = System.currentTimeMillis();
        this.counters = new AtomicInteger[windowNum];
        for (int i = 0; i < windowNum; i++) {
            counters[i] = new AtomicInteger(0);
        }
    }

    public boolean count() {
        lock.lock();
        try {
            long currTime = System.currentTimeMillis();
            // 计算滑动小窗口的数量
            long slideNum = Math.max(currTime - windowSize - reqTime, 0) / windowPeriod;
            if (slideNum > 0) {
                slideNum = Math.min(slideNum, windowNum);
            }
            for (int i = 0; i < slideNum; i++) {
                // 上一个访问窗口后的窗口都要清零
                currIndex = (currIndex + 1) % windowSize;
                counters[currIndex].set(0);
            }
            // 更新滑动窗口时间
            reqTime = reqTime + slideNum * windowPeriod;

            int count = 0;
            for(int i = 0; i < windowNum; i ++){
                count += counters[i].get();
            }
            if(count >= limit){
                return false;
            }else{
                counters[currIndex].incrementAndGet();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            lock.unlock();
        }
    }

    public int getSum() {
        int count = 0;
        for (AtomicInteger counter : counters) {
            count += counter.get();
        }
        return count;
    }

    public static void main(String[] args) {
        int total = 100;
        SlideWindowRateLimit2 slideWindowRateLimit = new SlideWindowRateLimit2(20, 1000, 10);
        ExecutorService service = Executors.newFixedThreadPool(1000);
        AtomicInteger sCount = new AtomicInteger(0);
        //
        for (int i = 0; i < total; i++) {
            service.execute(() -> {
                if (slideWindowRateLimit.count()) {
                    sCount.addAndGet(1);
                }
            });
        }
        System.out.println("done! total:[" + total + "] success:[" + sCount.get() + "]");
    }


}
