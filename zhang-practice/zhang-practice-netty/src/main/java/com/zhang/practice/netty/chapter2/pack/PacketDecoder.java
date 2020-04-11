package com.zhang.practice.netty.chapter2.pack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author : zzh
 * create at:  2020/4/3
 * @description:
 */
public class PacketDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> list) throws Exception {
        System.out.println("decode");
        System.out.println(byteBuf.readChar());
        list.add(byteBuf);
    }
}
