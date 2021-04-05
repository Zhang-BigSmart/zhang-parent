package com.zhang.practice.thread.oom;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author : zzh
 * create at:  2021/3/19
 * @description:
 */
public class OOMTest {


    public static void main(String[] args) {
        List<Baby> list = new ArrayList<>();
        long start = System.currentTimeMillis();

        try {
            while (true) {
                list.add(new Baby(new Random().nextInt(1024*1024)));
            }
        } finally {
            long end = System.currentTimeMillis() - start;
            System.out.println("cost time:" + end);
        }
    }


    static class Baby {
        private byte[] a;

        public Baby(int length) {
            this.a = new byte[length];
        }
    }
}
