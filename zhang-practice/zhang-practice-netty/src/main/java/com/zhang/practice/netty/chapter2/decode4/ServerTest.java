package com.zhang.practice.netty.chapter2.decode4;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetAddress;
import java.util.Arrays;
import java.util.List;

/**
 * @author : zzh
 * create at:  2019/10/30
 * @description:
 */
public class ServerTest {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new HelloServerInitializer());

            // 服务器绑定端口监听
            ChannelFuture f = bootstrap.bind(8080).sync();
            // 监听服务器关闭监听
            f.channel().closeFuture().sync();
            // 可以简写为
            /* b.bind(portNumber).sync().channel().closeFuture().sync(); */
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}

class HelloServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();

        // 以("\n")为结尾分割的 解码器
        // pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192,
        // Delimiters.lineDelimiter()));
        // 字符串解码 和 编码
        // pipeline.addLast(new IdleStateHandler(5 ,0, 0, TimeUnit.SECONDS));
        pipeline.addLast("encoder", new StringEncoder());
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("decoder1", new decoder1());
        pipeline.addLast("decoder2", new decoder2());
        pipeline.addLast("encoder1", new encoder1());
        pipeline.addLast("encoder2", new encoder2());


        // 自己的逻辑Handler
        pipeline.addLast("handler", new HelloServerHandler());
    }
}

class HelloServerHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        // 收到消息直接打印输出
        System.err.println("channelRead0");
        System.out.println(ctx.channel().remoteAddress() + " Say : " + msg);
        // 返回客户端消息 - 我已经接收到了你的消息
        ctx.writeAndFlush("Received your message !\n");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 收到消息直接打印输出
        System.err.println("channelRead");
        System.out.println(ctx.channel().remoteAddress() + " Say : " + msg);
        // 返回客户端消息 - 我已经接收到了你的消息
        ctx.writeAndFlush("Received your message !\n");

    }

    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("RamoteAddress : " + ctx.channel().remoteAddress() + " active !");
        ctx.writeAndFlush("Welcome to " + InetAddress.getLocalHost().getHostName() + " service!\n");
        super.channelActive(ctx);
    }
}

class encoder1 extends MessageToMessageEncoder<String> {

    @Override
    protected void encode(ChannelHandlerContext paramChannelHandlerContext, String paramI, List<Object> paramList)
            throws Exception {
        // TODO Auto-generated method stub
        System.err.println("encoder1:" + paramI);
        paramList.add(paramI);
        System.out.println(Arrays.toString(paramList.toArray()));
    }

}

class encoder2 extends MessageToMessageEncoder<String> {

    @Override
    protected void encode(ChannelHandlerContext paramChannelHandlerContext, String paramI, List<Object> paramList)
            throws Exception {
        // TODO Auto-generated method stub
        System.err.println("encoder2:" + paramI);
        paramList.add(paramI);
        System.out.println(Arrays.toString(paramList.toArray()));
    }
}

class decoder1 extends MessageToMessageDecoder<String> {

    @Override
    protected void decode(ChannelHandlerContext paramChannelHandlerContext, String paramI, List<Object> paramList)
            throws Exception {
        System.err.println("decoder1:" + paramI);
        paramList.add(paramI);
        System.out.println(Arrays.toString(paramList.toArray()));
    }

}

class decoder2 extends MessageToMessageDecoder<String> {

    @Override
    protected void decode(ChannelHandlerContext paramChannelHandlerContext, String paramI, List<Object> paramList)
            throws Exception {
        // TODO Auto-generated method stub
        System.err.println("decoder2:" + paramI);
        paramList.add(paramI);
        System.out.println(Arrays.toString(paramList.toArray()));
    }
}
