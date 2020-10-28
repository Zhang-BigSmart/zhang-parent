package com.zhang.practice.thread.jmx.pool;

/**
 * @author : zzh
 * create at:  2020/8/23
 * @description:
 */
public class ExecutorServiceMonitor
        implements ExecutorServiceMonitorMXBean {

    @Override
    public double getRequestPerSecondRetirementRate() {
        return 0;
    }

    @Override
    public double getAverageServiceTime() {
        return 0;
    }

    @Override
    public double getAverageTimeWaitingInPool() {
        return 0;
    }

    @Override
    public double getAverageResponseTime() {
        return this.getAverageServiceTime() +
                this.getAverageTimeWaitingInPool();
    }

    @Override
    public double getEstimatedAverageNumberOfActiveRequests() {
        return getRequestPerSecondRetirementRate() * (getAverageServiceTime() +
                getAverageTimeWaitingInPool());
    }

    @Override
    public double getRatioOfDeadTimeToResponseTime() {
        return 0;
    }

    @Override
    public double v() {
        return 0;
    }

}
