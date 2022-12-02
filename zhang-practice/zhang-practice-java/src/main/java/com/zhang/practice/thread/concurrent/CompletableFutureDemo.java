package com.zhang.practice.thread.concurrent;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * <p> TODO
 *
 * @author 哲也
 * @since 2021/5/21
 */
public class CompletableFutureDemo {

    public static void main(String[] args) {
        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(CompletableFutureDemo::getCount);
        //Function
        //cf.thenAccept()
    }

    public static Integer getCount() {
        int i = new Random().nextInt(100);
        System.out.println("==i==");
        return i;
    }
}
