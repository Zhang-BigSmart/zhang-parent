package com.zhang.practice.netty.chapter2.pack;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * @ClassName TimeServerHandler
 * @Description:
 * @Author: zhangzh
 * @Date 2019/3/22 13:54
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    private int counter;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("TimeServerHandler");
        String req = (String) msg;
        System.out.println("server receive:" + req);

        String resp = "ok, server have got you message";
        ctx.writeAndFlush(Unpooled.copiedBuffer(resp.getBytes()));
        if ("dynamic".contains(req)) {
            ctx.pipeline().addLast(new DynamicHandler());
        }
        //ctx.fireChannelRead(msg);
//        ByteBuf buf = (ByteBuf) msg;
//        byte[] req = new byte[buf.readableBytes()];
//        buf.readBytes(req);
////        String body = new String(req, "UTF-8").substring(0, req.length - System.getProperty("line.separator").length());
//        String body = new String(req, "UTF-8");
//        System.out.println("The time server receive order :" + body + "; the counter is: " + ++counter);
//        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
//        currentTime = currentTime + System.getProperty("line.separator");
//        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
//        ctx.writeAndFlush(resp);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
