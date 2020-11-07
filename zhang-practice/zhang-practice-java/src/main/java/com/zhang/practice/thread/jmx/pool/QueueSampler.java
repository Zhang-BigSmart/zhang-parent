package com.zhang.practice.thread.jmx.pool;

import java.util.Date;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * @author : zzh
 * create at:  2020/8/23
 * @description:
 */
public class QueueSampler implements QueueSamplerMXBean {

    private Queue<String> queue;

    private Set<String> ips = new HashSet<>();

    public QueueSampler (Queue<String> queue) {
        this.queue = queue;
    }

    @Override
    public QueueSample getQueueSample() {
        synchronized (queue) {
            return new QueueSample(new Date(),
                    queue.size(), queue.peek());
        }
    }

    @Override
    public void clearQueue() {
        synchronized (queue) {
            queue.clear();
        }
    }

    @Override
    public void addQueue(String element) {
        synchronized (queue) {
            queue.add(element);
        }
    }


    public Queue<String> getQueue() {
        return queue;
    }
}
