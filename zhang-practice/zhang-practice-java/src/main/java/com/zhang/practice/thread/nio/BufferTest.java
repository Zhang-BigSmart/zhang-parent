package com.zhang.practice.thread.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author : zzh
 * create at:  2021/3/16
 * @description:
 */
public class BufferTest {

    public static void main(String[] args) throws IOException {
        String filePath = ClassLoader.getSystemClassLoader().getResource("channel/hello.txt").getPath();

        RandomAccessFile aFile = new RandomAccessFile(filePath, "rw");
        FileChannel inChannel = aFile.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(2);

        while (inChannel.read(buf) != -1) {
            // make buffer ready for read
            buf.flip();

            while (buf.hasRemaining()) {
                // read 1 byte at a time
                System.out.print((char) buf.get());
            }

            buf.clear();
        }
        aFile.close();
    }
}
