package com.zhang.practice.netty.chapter3;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * @author : zzh
 * create at:  2019/11/16
 * @description:
 */
public class ByteBufferTest {

    public static void main(String[] args) {
        ByteBuffer bbuf = ByteBuffer.allocate(10);
        int capacity = bbuf.capacity();
        System.out.println(capacity);
        //bbuf.putShort(2,(short)123);

        ByteBuffer bb = bbuf.slice();
        bb.putShort(2,(short)123);

        System.out.println(Arrays.toString(bbuf.array()));
        System.out.println(Arrays.toString(bb.array()));
    }
}
