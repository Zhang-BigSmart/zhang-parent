package com.zhang.practice.netty.chapter3;

import com.zhang.practice.netty.chapter3.model.DataTest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author : zzh
 * create at:  2019/10/31
 * @description:
 */
public class ProtoServerHandler extends SimpleChannelInboundHandler<DataTest.RequestUser> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataTest.RequestUser msg) throws Exception {
        DataTest.RequestUser.parseFrom(msg.get);
        System.out.println(msg.getUserName());
        System.out.println(msg.getAge());
        System.out.println(msg.getPassword());

        DataTest.ResponseBank bank = DataTest.ResponseBank.newBuilder().setBankName("中国工商银行")
                .setBankNo("6222222200000000000").setMoney(560000.23).build();

        ctx.channel().writeAndFlush(bank);
    }
}
