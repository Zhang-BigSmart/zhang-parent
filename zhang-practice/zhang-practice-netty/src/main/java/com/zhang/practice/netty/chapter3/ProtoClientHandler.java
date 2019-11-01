package com.zhang.practice.netty.chapter3;

import com.zhang.practice.netty.chapter3.model.DataInfo;
import com.zhang.practice.netty.chapter3.model.DataTest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author : zzh
 * create at:  2019/10/31
 * @description:
 */
public class ProtoClientHandler extends SimpleChannelInboundHandler<DataTest.ResponseBank> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataTest.ResponseBank msg) throws Exception {
        System.out.println(msg.getBankNo());
        System.out.println(msg.getBankName());
        System.out.println(msg.getMoney());
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        DataInfo.RequestUser user = DataInfo.RequestUser.newBuilder()
                .setUserName("zhihao.miao").setAge(27).setPassword("123456").build();
        ctx.channel().writeAndFlush(user);
    }
}
