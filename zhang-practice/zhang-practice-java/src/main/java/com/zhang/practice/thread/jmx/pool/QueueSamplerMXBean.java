package com.zhang.practice.thread.jmx.pool;

/**
 * @author : zzh
 * create at:  2020/8/23
 * @description:
 */
public interface QueueSamplerMXBean {

    QueueSample getQueueSample();

    void clearQueue();

    void addQueue(String element);

}
