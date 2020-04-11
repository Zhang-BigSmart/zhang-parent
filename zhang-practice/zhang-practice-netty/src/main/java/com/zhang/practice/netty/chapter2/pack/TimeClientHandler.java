package com.zhang.practice.netty.chapter2.pack;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @ClassName TimeClientHandler
 * @Description:
 * @Author: zhangzh
 * @Date 2019/3/22 14:24
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {

    private int counter;

    private byte[] req;

    public TimeClientHandler() {
        req = ("QUERY TIME ORDER" + System.getProperty("line.separator")).getBytes();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("TimeClientHandler channelActive");
        byte[] bytes = ("dynamic Client active ！！！").getBytes();
        ByteBuf message = Unpooled.buffer(bytes.length);
        message.writeBytes(bytes);
        ctx.writeAndFlush(message);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("TimeClientHandler channelRead");
        //ByteBuf buf = (ByteBuf) msg;
        /*byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, "UTF-8");*/
        String body = (String) msg;
        System.out.println("Now is:" + body + "; the counter is:" + ++counter);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause.getMessage());
        ctx.close();
    }
}
