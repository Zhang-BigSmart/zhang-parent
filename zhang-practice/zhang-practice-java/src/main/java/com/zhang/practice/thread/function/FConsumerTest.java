package com.zhang.practice.thread.function;

import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * <p> TODO
 *
 * @author 哲也
 * @since 2021/5/25
 */
public class FConsumerTest {


    public static void main(String[] args) {
        Consumer<String> consumer = (str) -> System.out.println(str);
        // 1. 直接执行accept方法
        consumer.accept("hello world!");
        // 2. 配合stream使用
        Stream.of("1", "2", "3").forEach(consumer);

        Supplier<Integer> supplier = () -> new Random().nextInt();
        System.out.println(supplier.get());
    }
}
