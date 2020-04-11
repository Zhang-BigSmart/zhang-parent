package com.zhang.practice.netty.chapter4.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.TooLongFrameException;

import java.util.List;

/**
 * @author : zzh
 * create at:  2020/4/10
 * @description:
 */
public class FrameChunkDecoder extends ByteToMessageDecoder {

    private final int maxFrameSize;

    public FrameChunkDecoder(int maxFrameSize) {
        this.maxFrameSize = maxFrameSize;
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int readableBytes = byteBuf.readableBytes();
        if (readableBytes > maxFrameSize) {
            // discard the bytes
            byteBuf.clear();
            throw new TooLongFrameException();
        }
        ByteBuf buf = byteBuf.readBytes(readableBytes);
        list.add(buf);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("exceptionCaught");
        if (cause instanceof TooLongFrameException) {
            System.out.println("TooLongFrameException");
        }
        super.exceptionCaught(ctx, cause);
    }
}
