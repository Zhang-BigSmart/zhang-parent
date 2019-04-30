package com.zhang.practice.netty.chapter1.nio;

/**
 * @ClassName TimeClent
 * @Description:
 * @Author: zhangzh
 * @Date 2019/3/17 16:29
 */
public class TimeClent {

    public static void main(String[] args) {
        int port = 8008;
        new Thread(new TimeClientHandle("127.0.0.1", port), "TimeClient-001")
                .start();
    }
}
