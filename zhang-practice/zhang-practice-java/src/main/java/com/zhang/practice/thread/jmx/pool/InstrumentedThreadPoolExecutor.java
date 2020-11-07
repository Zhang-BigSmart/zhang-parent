package com.zhang.practice.thread.jmx.pool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author : zzh
 * create at:  2020/8/23
 * @description:
 */
public class InstrumentedThreadPoolExecutor extends ThreadPoolExecutor {

    // Keep track of all of the request times
    private final ConcurrentHashMap<Runnable, Long> timeOfRequest =
            new ConcurrentHashMap<>();
    private final ThreadLocal<Long> startTime = new ThreadLocal<Long>();
    private long lastArrivalTime;
    private AtomicLong totalServiceTime;
    private AtomicLong totalPoolTime;
    private AtomicLong numberOfRequestsRetired;
    private AtomicLong numberOfRequests;
    private AtomicLong aggregateInterRequestArrivalTime;


    public InstrumentedThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }
    // other variables are AtomicLongs and AtomicIntegers

    @Override
    protected void beforeExecute(Thread worker, Runnable task) {
        super.beforeExecute(worker, task);
        // 线程的开始时间
        startTime.set(System.nanoTime());
    }

    @Override
    protected void afterExecute(Runnable task, Throwable t) {
        try {
            // 总的服务时间
            totalServiceTime.addAndGet(System.nanoTime() - startTime.get());

            totalPoolTime.addAndGet(startTime.get() - timeOfRequest.remove(task));

            numberOfRequestsRetired.incrementAndGet();
        } finally {
            super.afterExecute(task, t);
        }
    }

    @Override
    public void execute(Runnable task) {
        long now = System.nanoTime();

        numberOfRequests.incrementAndGet();
        synchronized (this) {
            if (lastArrivalTime != 0L) {
                aggregateInterRequestArrivalTime.addAndGet(now - lastArrivalTime);
            }
            lastArrivalTime = now;
            timeOfRequest.put(task, now);
        }
        super.execute(task);
    }
}
