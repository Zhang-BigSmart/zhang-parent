package com.zhang.practice.netty.chapter2.decode3;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @ClassName EchoServerHandler
 * @Description:
 * @Author: zhangzh
 * @Date 2019/3/22 16:21
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    int counter = 0;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("This is " + ++ counter + " times receive client:[" + msg + "]");
    }
}
