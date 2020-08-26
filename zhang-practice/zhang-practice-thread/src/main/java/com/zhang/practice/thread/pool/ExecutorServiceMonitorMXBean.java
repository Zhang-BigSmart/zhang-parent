package com.zhang.practice.thread.pool;

/**
 * @author : zzh
 * create at:  2020/8/23
 * @description:
 */
public interface ExecutorServiceMonitorMXBean {

    double getRequestPerSecondRetirementRate();

    double getAverageServiceTime();

    double getAverageTimeWaitingInPool();

    double getAverageResponseTime();

    double getEstimatedAverageNumberOfActiveRequests();

    double getRatioOfDeadTimeToResponseTime();

    double v();
}
