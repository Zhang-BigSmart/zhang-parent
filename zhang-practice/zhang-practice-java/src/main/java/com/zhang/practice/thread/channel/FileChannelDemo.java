package com.zhang.practice.thread.channel;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author : zzh
 * create at:  2020/2/4
 * @description:
 */
public class FileChannelDemo {

    public static void main(String[] args) throws IOException {
//        channelRead();
//        channelWrite();
//        mappedWrite();
//        mappedRead();
        ByteBuffer byteBufferRead = ByteBuffer.allocate(1024 * 1024);
        System.out.println(byteBufferRead.position());
        System.out.println(byteBufferRead.limit());
        System.out.println(byteBufferRead.capacity());
        System.out.println(byteBufferRead.hasRemaining());

    }

    public static void channelRead() throws IOException {
        String filePath = ClassLoader.getSystemClassLoader().getResource("channel/hello.txt").getPath();
        try(RandomAccessFile reader = new RandomAccessFile(filePath, "r");
            FileChannel channel = reader.getChannel();
            ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            int bufferSize = 1024;
            if (bufferSize > channel.size()) {
                bufferSize = (int) channel.size();
            }
            ByteBuffer buf = ByteBuffer.allocate(bufferSize);
            buf.clear();

            while (channel.read(buf) > 0) {
                out.write(buf.array(), 0, buf.position());
                buf.clear();
            }

            String content = new String(out.toByteArray(), StandardCharsets.UTF_8);
            System.out.println(content);
        }
    }

    public static void channelWrite() throws IOException  {
        String filePath = ClassLoader.getSystemClassLoader().getResource("channel/hello.txt").getPath();
        System.out.println(filePath);
        try(RandomAccessFile writer = new RandomAccessFile(filePath, "rw");
            FileChannel channel = writer.getChannel()) {
            ByteBuffer buff = ByteBuffer.wrap("wake up".getBytes(StandardCharsets.UTF_8));
            channel.write(buff);
        }
    }

    public static void mappedWrite() throws IOException {
        String filePath = ClassLoader.getSystemClassLoader().getResource("channel/hello.txt").getPath();
        try(RandomAccessFile writer = new RandomAccessFile(filePath, "rw");
            FileChannel channel = writer.getChannel()) {
            byte[] content = "mappedWrite!!!".getBytes(StandardCharsets.UTF_8);
            MappedByteBuffer buff = channel.map(FileChannel.MapMode.READ_WRITE, 0, content.length);
            buff.put(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void mappedRead() throws IOException {
        String filePath = ClassLoader.getSystemClassLoader().getResource("channel/hello.txt").getPath();
        try(RandomAccessFile reader = new RandomAccessFile(filePath, "r");
            FileChannel channel = reader.getChannel()) {
            System.out.println(channel.size());
            MappedByteBuffer buff = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
            if(buff.hasRemaining()) {
                byte[] data = new byte[buff.remaining()];
                buff.get(data);
                System.out.println(new String(data, StandardCharsets.UTF_8));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
