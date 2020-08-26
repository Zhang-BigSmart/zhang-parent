package com.zhang.practice.thread.pool;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author : zzh
 * create at:  2020/8/23
 * @description:
 */
public class PoolMain {

    public static int corePoolSize = 4;
    public static int maximumPoolSize = 10;
    public static long keepAliveTime = 1000;
    public static LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(10);

    InstrumentedThreadPoolExecutor executor =
            new InstrumentedThreadPoolExecutor(
                    corePoolSize,
                    maximumPoolSize,
                    keepAliveTime,
                    TimeUnit.MILLISECONDS,
                    workQueue);


    public static void main(String[] args) throws Exception {
        MBeanServer mbs =
                ManagementFactory.getPlatformMBeanServer();

        ObjectName mxbeanName = new ObjectName("com.zhang.practice.thread.pool:type=QueueSampler");
        Queue<String> queue = new ArrayBlockingQueue<String>(10);
        queue.add("Request-1");
        queue.add("Request-2");
        queue.add("Request-3");
        QueueSampler mxbean = new QueueSampler(queue);

        mbs.registerMBean(mxbean, mxbeanName);
        System.out.println("Waiting...");
        Thread.sleep(Long.MAX_VALUE);
    }
}
