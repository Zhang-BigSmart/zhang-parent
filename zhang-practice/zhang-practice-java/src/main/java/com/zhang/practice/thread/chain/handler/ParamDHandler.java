package com.zhang.practice.thread.chain.handler;

import com.zhang.practice.thread.Request;
import com.zhang.practice.thread.chain.StrategyHandler;

/**
 * @author : zzh
 * create at:  2021/1/26
 * @description:
 */
public class ParamDHandler implements StrategyHandler<Request, String> {

    @Override
    public String apply(Request param) {
        return this.getClass().getSimpleName();
    }
}
