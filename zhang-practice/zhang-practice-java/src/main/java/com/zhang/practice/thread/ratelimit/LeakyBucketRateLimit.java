package com.zhang.practice.thread.ratelimit;

import lombok.Data;
import lombok.SneakyThrows;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : zzh
 * create at:  2020/11/26
 * @description:漏桶限流算法 重点：
 * 1.漏桶容量大小
 * 2.漏水速率
 * 类似生产者和消费者、队列方式
 */
public class LeakyBucketRateLimit implements Runnable {

    private LinkedBlockingQueue<Request> list;

    private ScheduledExecutorService scheduledExecutorService;

    public LeakyBucketRateLimit(int rate, int size) {
        this.list = new LinkedBlockingQueue<>(size);

        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        long interval = 1000 / rate;
        scheduledExecutorService.scheduleAtFixedRate(this, 0, interval, TimeUnit.MILLISECONDS);
    }

    @Override
    public void run() {
        Request request = list.poll();
        if (request != null) {
            request.setDealTime(new Date());
            System.out.println(request + " | " + "accept");
        }
    }

    @Data
    static class Request {
        private int id;
        private Date reqTime;
        private Date dealTime;


        public Request(int id) {
            this.id = id;
            this.reqTime = new Date();
        }

        @Override
        public String toString() {
            return "Request{" +
                    "id=" + id +
                    ", reqTime=" + dateToString(reqTime) +
                    ", dealTime=" + dateToString(dealTime) +
                    '}';
        }
    }

    public boolean count(Request request) {
        // 如果队列满，会返回false
        boolean result = list.offer(request);
        System.out.println("requestId:" + request.getId() + " result:" + result);
        return result;
        /*if (!list.offer(request)) {
            System.out.println(request + " | " + "refuse");
        }
        return false;*/
    }

    public static String dateToString(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String dateString = formatter.format(date);
        return dateString;
    }

    public static void main(String[] args) {
        //  每秒2个，大小为10
        LeakyBucketRateLimit leakyBucketRateLimit = new LeakyBucketRateLimit(2, 10);
        int total = 100;
        ExecutorService service = Executors.newFixedThreadPool(1000);
        AtomicInteger sCount = new AtomicInteger(0);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(total);
        for (int i = 0; i < total; i++) {
            Request request = new Request(i);
            service.submit(() -> {
                try {
                    cyclicBarrier.await();
                    if (leakyBucketRateLimit.count(request)) {
                        sCount.incrementAndGet();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        System.out.println("done! total:[" + total + "] success:[" + sCount.get() + "]");


    }


}
