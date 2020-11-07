package com.zhang.practice.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

/**
 * @author : zzh
 * create at:  2019/11/6
 * @description:
 */
public class ShutDownHookTest {

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                close();
            }
        }));
    }

    public static void close() {
        System.out.println("shut down closing!!!");
    }

    public static int pid() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        System.out.println(runtimeMXBean.getName());
        return Integer.valueOf(runtimeMXBean.getName().split("@")[0]).intValue();
    }

    public static void main(String[] args) {
        System.out.println(ShutDownHookTest.pid());
        while (true) {

        }


    }
}
