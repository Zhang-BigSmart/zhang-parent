package com.zhang.practice.netty.chapter1.netty.chapter2;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author : zzh
 * create at:  2019/10/26
 * @description:
 */
public class InboundHandlerB extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("InboundHandler B : ");
        // 传播read事件到下一个channelhandler
        ctx.fireChannelRead(msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // channel激活，触发channelRead事件，从pipeline的heandContext节点开始往下传播
        System.out.println("InboundHandlerB channelActive");
//        ctx.channel().pipeline().fireChannelRead("Hello world");
    }

}
