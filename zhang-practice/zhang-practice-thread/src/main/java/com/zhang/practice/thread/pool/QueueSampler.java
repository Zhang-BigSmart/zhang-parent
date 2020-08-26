package com.zhang.practice.thread.pool;

import java.util.Date;
import java.util.Queue;

/**
 * @author : zzh
 * create at:  2020/8/23
 * @description:
 */
public class QueueSampler
        implements QueueSamplerMXBean {

    private Queue<String> queue;

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
}
