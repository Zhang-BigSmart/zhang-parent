package com.zhang.practice.thread;

/**
 * @ClassName Interrupt
 * @Description:
 * @Author: zhangzh
 * @Date 2019/3/8 17:15
 */
public class Interrupt {

    private static class MyThread1 extends Thread{

        @Override
        public void run() {
            try {
                System.out.println("start");
                Thread.sleep(3000);
                System.out.println("end");
            } catch (InterruptedException e) {
                System.out.println("InterruptedException");
                e.printStackTrace();
            }
        }
    }

    private static class MyThread2 extends Thread {

        @Override
        public void run() {
            System.out.println("do something");
            while (!interrupted()) {
                // ..
            }
            System.out.println("Thread end");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread2 t1 = new MyThread2();
        t1.start();
//        Thread.sleep(3000);
        t1.interrupt();
        System.out.println("main end");
    }

}


