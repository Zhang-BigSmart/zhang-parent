package com.zhang.practice.netty.chapter4.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * @author : zzh
 * create at:  2020/4/9
 * @description:
 */
public class AbsIntegerEncoder extends MessageToMessageEncoder<ByteBuf> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        while (byteBuf.readableBytes() >= 4) {
            int raw = byteBuf.readInt();
            System.out.println("raw value: " + raw);
            int value = Math.abs(raw);
            list.add(value);
        }
    }
}
