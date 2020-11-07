package com.zhang.practice.thread.notify;

/**
 * @author : zzh
 * create at:  2020/2/10
 * @description:
 */
public class NotifyDemo {

    public static void main(String[] args) {
        //写两个线程 1.图片下载
        final Object obj = new Object();
        Thread download = new Thread(() -> {
            System.out.println("开始下载图片");
            for (int i = 0; i < 101; i+=10) {
                System.out.println("down"+i+"%");
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("图片下载成功");
            synchronized (obj) {
                obj.notifyAll();//唤起
            }
            System.out.println("开始下载附件");
            for (int i = 0; i < 101; i+=10) {
                System.out.println("附件下载"+i+"%");

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("附件下载成功");
        });
        //2.图片展示
        Thread show = new Thread(() -> {
            synchronized (obj) {
                System.out.println("==show==");
                try {
                    obj.wait(400);//阻塞当前
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("show:开始展示图片");
                System.out.println("图片展示完毕");
            }

        });

        Thread test = new Thread(() -> {
            synchronized (obj) {
                try {
                    obj.wait();//阻塞当前
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("=========");
                System.out.println("test");

            }
        });

        download.start();
        show.start();
        test.start();
    }
}
