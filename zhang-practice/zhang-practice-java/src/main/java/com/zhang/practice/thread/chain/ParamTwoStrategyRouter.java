package com.zhang.practice.thread.chain;

import com.zhang.practice.thread.Request;
import com.zhang.practice.thread.chain.handler.ParamAHandler;
import com.zhang.practice.thread.chain.handler.ParamBHandler;

/**
 * @author : zzh
 * create at:  2021/1/26
 * @description:
 */
public class ParamTwoStrategyRouter extends AbstractStrategyRouter<Request, String> implements StrategyHandler<Request, String>{

    @Override
    protected StrategyMapper<Request, String> registerStrategyMapper() {

        return param -> {
            if ("C".equals(param.getParam())) {
                return new ParamAHandler();
            }else if ("D".equals(param.getParam())) {
                return new ParamBHandler();
            }
            return null;
        };
    }

    @Override
    public String apply(Request param) {
        return this.applyStrategy(param);
    }


}
