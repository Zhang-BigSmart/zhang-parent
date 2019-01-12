package com.zhang.practice.thread.scheduled;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * @ClassName Test
 * @Description:
 * @Author: zhangzh
 * @Date 2018/10/17 16:57
 */
public class Test {

    public static void main(String[] args) {

        // 线程池维护线程的最少数量
        int CORE_POOL_SIZE = 5;

        // 线程池维护线程的最大数量
        int MAX_POOL_SIZE = 60;

        // 线程池维护线程所允许的空闲时间
        int KEEP_ALIVE_TIME = 180;

        // 线程池所使用的缓冲队列大小
        int WORK_QUEUE_SIZE = 50000;

        // 请求Request缓冲队列
        Queue<Runnable> msgQueue = new LinkedList<>();

        final RejectedExecutionHandler handler = (r, executor) -> {
            System.out.println("request 放入队列中重新等待执行");
            msgQueue.offer(r);
        };


        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME,
                TimeUnit.SECONDS, new ArrayBlockingQueue(WORK_QUEUE_SIZE), handler);


        // 创建的线程长度为Integer.MAX_VALUE，容易发生内存溢出
        // 内部也是使用ScheduledThreadPoolExecutor创建对象
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);


        System.out.println("main开始时间" + MyThread.nowTime());
        for (int i = 0; i < 3; i++) {
            MyThread myThread = new MyThread("thread" + i);
            System.out.println("thread" + i + "开始时间" + MyThread.nowTime());
            scheduledExecutorService.schedule(myThread, 5, TimeUnit.SECONDS);//延时5s 执行
            //scheduledExecutorService.scheduleWithFixedDelay(myThread, 5, 2, TimeUnit.SECONDS);
        }
        try {
            Thread.sleep(70000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scheduledExecutorService.shutdown();//7s 后关闭不再接受执行线程
        while (!scheduledExecutorService.isTerminated()) {//all thread等待结束
        }
        System.out.println("main结束时间" + MyThread.nowTime());
    }
}
