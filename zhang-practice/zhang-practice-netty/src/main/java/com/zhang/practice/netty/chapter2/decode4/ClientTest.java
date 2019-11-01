package com.zhang.practice.netty.chapter2.decode4;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.io.IOException;


/**
 * @author : zzh
 * create at:  2019/10/30
 * @description:
 */
public class ClientTest {

    public static void main(String[] args) throws InterruptedException, IOException {

        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new HelloClientInitializer());
        // 连接服务端
        try {
            // 1.
            // Channel ch = bootstrap.connect("127.0.0.1",
            // 8080).sync().channel();
            // // 控制台输入
            // BufferedReader in = new BufferedReader(new
            // InputStreamReader(System.in));
            // for (;;) {
            // String line = in.readLine();
            // if (line == null) {
            // continue;
            // }
            // /*
            // * 向服务端发送在控制台输入的文本 并用"\r\n"结尾
            // * 之所以用\r\n结尾 是因为我们在handler中添加了 DelimiterBasedFrameDecoder 帧解码。
            // * 这个解码器是一个根据\n符号位分隔符的解码器。所以每条消息的最后必须加上\n否则无法识别和解码
            // * */
            // ch.writeAndFlush(line + "\r\n");
            // }
            // 2.
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8080).sync();
            channelFuture.channel().writeAndFlush("Hello Netty Server ,I am a common client");
            channelFuture.channel().closeFuture().sync();

        } finally {
            // The connection is closed automatically on shutdown.
            eventLoopGroup.shutdownGracefully();
        }
    }
}

class HelloClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // TODO Auto-generated method stub
        /*
         * 这个地方的 必须和服务端对应上。否则无法正常解码和编码
         *
         * 解码和编码 我将会在下一张为大家详细的讲解。再次暂时不做详细的描述
         *
         */
        ChannelPipeline pipeline = ch.pipeline();
        // pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192,
        // Delimiters.lineDelimiter()));
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());

        // 客户端的逻辑
        pipeline.addLast("handler", new HelloClientHandler());
    }

}

class HelloClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client active ");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client close ");
        super.channelInactive(ctx);
    }
    @Override
    protected void channelRead0(ChannelHandlerContext paramChannelHandlerContext, String msg) throws Exception {
        System.out.println("Server say : " + msg);

    }
}

class test1 extends ChannelOutboundHandlerAdapter {

}
