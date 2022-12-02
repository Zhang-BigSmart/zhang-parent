package com.zhang.practice.thread.reactor;

import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author : zzh
 * create at:  2022/7/28
 * @description:
 */
public class ReactorDemo {

    public static void main(String[] args) {
        Flux.just("tom", "jack", "allen")
                .filter(s -> s.length() > 3)
                .map(s -> s.concat("@qq.com"))
                .subscribe(System.out::println);


        List<String> words = Arrays.asList("th", "qu");
        Flux.fromIterable(words)
                .flatMap(word -> {
                    System.out.println("Step1=" + word);
                    return Flux.fromArray(word.split(""));
                })
                .filter(s -> {
                    System.out.println("Step2=" + s);
                    return true;
                }).filter(s -> {
                    System.out.println("Step3=" + s);
                    return true;
                }).subscribe(s -> System.out.println("Result=" + s + "\n"));



        /*EmitterProcessor<Integer> emitterProcessor = EmitterProcessor.create(4);

        Flux<Integer> flux1 = emitterProcessor.filter(e -> e % 2 == 0);
        Flux<Integer> flux2 = emitterProcessor.filter(e -> e % 3 == 0);

        System.err.println("start subscribe ");

        flux1.subscribe(System.err::println);

        IntStream.rangeClosed(1,10)
                .forEach(e -> {

                    emitterProcessor.onNext(e);
                    System.err.println("emmit data " + e);
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }});

        //flux2 没有消费1~10,只能消费后面的3
        flux2.subscribe(System.err::println);
        emitterProcessor.onNext(3);
        emitterProcessor.onComplete();*/


    }
}
