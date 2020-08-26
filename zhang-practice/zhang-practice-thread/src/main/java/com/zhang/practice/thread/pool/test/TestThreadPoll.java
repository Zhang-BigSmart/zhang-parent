package com.zhang.practice.thread.pool.test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author : zzh
 * create at:  2020/8/23
 * @description:
 */
public class TestThreadPoll extends ThreadPoolExecutor {

    public TestThreadPoll(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    public void execute(Runnable command) {
        super.execute(command);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        System.out.println("queue size:" + this.getQueue().size());
        System.out.println( "task count:" + this.getTaskCount());
        System.out.println("pool active size:" + this.getActiveCount());
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
    }
}
