package com.zhang.practice.netty.chapter2.pack;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : zzh
 * create at:  2020/4/4
 * @description:
 */
public class DynamicHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("dynamic handler");
        //super.channelRead(ctx, msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.newCondition();
        System.out.println("DynamicHandler channelActive");
        super.channelActive(ctx);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        super.handlerRemoved(ctx);
    }
}
