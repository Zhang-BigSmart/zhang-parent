package com.zhang.practice.netty.chapter1.nio;

/**
 * @ClassName TimerServer
 * @Description:
 * @Author: zhangzh
 * @Date 2019/3/17 13:23
 */
public class TimerServer {

    public static void main(String[] args) {
        int port = 8008;
        MultiplexerTimerServer timerServer = new MultiplexerTimerServer(port);

        new Thread(timerServer, "NIO-MultiplexerTimeServer-001").start();
    }

}
