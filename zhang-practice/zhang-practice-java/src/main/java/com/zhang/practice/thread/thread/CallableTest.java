package com.zhang.practice.thread.thread;

import java.util.concurrent.*;

/**
 * <p> TODO
 *
 * @author
 * @since 2021/4/24
 */
public class CallableTest {

    public static void main(String[] args) {

        ExecutorService es = Executors.newSingleThreadExecutor();

        CallableDemo callableDemo = new CallableDemo();

        Future<Integer> future = es.submit(callableDemo);


        //FutureTask

        //关闭线程池
        es.shutdown();
        try {
            Thread.sleep(2000);
            System.out.println("主线程在执行其他任务");

            if(future.get()!=null){
                //输出获取到的结果
                System.out.println("future.get()-->"+future.get());
            }else{
                //输出获取到的结果
                System.out.println("future.get()未获取到结果");
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("主线程在执行完成");
    }

}

class CallableDemo implements Callable<Integer> {

    private int sum;
    @Override
    public Integer call() throws Exception {
        System.out.println("Callable子线程开始计算啦！");
        Thread.sleep(2000);

        for(int i=0 ;i<5000;i++){
            sum=sum+i;
        }
        System.out.println("Callable子线程计算结束！");
        return sum;

    }
}
