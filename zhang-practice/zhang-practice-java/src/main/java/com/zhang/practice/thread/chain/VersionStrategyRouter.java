package com.zhang.practice.thread.chain;

import com.zhang.practice.thread.Request;
import com.zhang.practice.thread.chain.handler.ParamBHandler;

/**
 * @author : zzh
 * create at:  2021/1/26
 * @description:
 */
public class VersionStrategyRouter extends AbstractStrategyRouter<Request, String> implements StrategyHandler<Request, String>{

    @Override
    protected StrategyMapper<Request, String> registerStrategyMapper() {
        return param -> {
            if ("one".equals(param.getVersion())) {
                return new ParamOneStrategyRouter();
            }else if ("two".equals(param.getVersion())) {
                return new ParamTwoStrategyRouter();
            }
            return null;
        };
    }

    @Override
    public String apply(Request param) {
        return this.applyStrategy(param);
    }
}
