package com.zhang.practice.thread.chain;

/**
 * @author : zzh
 * create at:  2021/1/26
 * @description:
 */
public interface StrategyHandler<T, R> {

    @SuppressWarnings("rawtypes")
    StrategyHandler DEFAULT = t -> null;

    /**
     * apply strategy
     *
     * @param param
     * @return
     */
    R apply(T param);
}
