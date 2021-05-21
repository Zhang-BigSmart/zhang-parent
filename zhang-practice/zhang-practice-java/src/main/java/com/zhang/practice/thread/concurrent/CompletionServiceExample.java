package com.zhang.practice.thread.concurrent;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * <p> TODO
 *
 * @author 哲也
 * @since 2021/5/21
 */
public class CompletionServiceExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        List<Callable<Integer>> callableList = Arrays.asList(
                () -> {
                    mySleep(10);
                    System.out.println("==5 end==");
                    return 10;
                    },
                () -> {
                    mySleep(5);
                    System.out.println("==5 end==");
                    return 5;
                }
        );

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        ExecutorCompletionService completionService = new ExecutorCompletionService(executorService);

        completionService.submit(callableList.get(0));
        completionService.submit(callableList.get(1));

        // 获取 future 结果，不会阻塞
        Future<Integer> poll = completionService.poll();
        System.out.println(poll);

        // 获取 future 结果，最多等待3s，不会阻塞
        Future pollTimeout = completionService.poll(3, TimeUnit.SECONDS);
        System.out.println(pollTimeout);

        // 通过 take 获取 future 结果，此方法会阻塞
        for (int i = 0; i < callableList.size(); i++) {
            System.out.println(completionService.take().get());
        }

        System.out.println("==main end==");

    }

    private static void mySleep(int seconds) throws InterruptedException {
        TimeUnit.SECONDS.sleep(seconds);
    }
}
