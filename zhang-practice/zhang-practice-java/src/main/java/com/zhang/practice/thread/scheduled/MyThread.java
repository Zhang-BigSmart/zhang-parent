package com.zhang.practice.thread.scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName MyThread
 * @Description:
 * @Author: zhangzh
 * @Date 2018/10/17 16:57
 */
public class MyThread implements Runnable {

    private String name;
    public MyThread(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    @Override
    public void run() {
        System.out.println(getName() + "true start:" + nowTime());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(getName() + "true end:" + nowTime());

    }
    public static String nowTime() {
        SimpleDateFormat dataFormate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dataFormate.format(new Date());
    }
}
