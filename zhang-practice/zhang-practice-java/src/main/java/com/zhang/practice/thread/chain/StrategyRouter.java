package com.zhang.practice.thread.chain;

/**
 * @author : zzh
 * create at:  2021/1/26
 * @description:
 */
public class StrategyRouter extends AbstractStrategyRouter<String, Integer> implements StrategyHandler<String, Integer> {

    @Override
    protected StrategyMapper<String, Integer> registerStrategyMapper() {



        return null;
    }

    @Override
    public Integer apply(String param) {
        return null;
    }

    static class ExeStrategyMapper implements StrategyMapper<String, Integer> {

        @Override
        public StrategyHandler<String, Integer> get(String param) {
            return null;
        }
    }
}
