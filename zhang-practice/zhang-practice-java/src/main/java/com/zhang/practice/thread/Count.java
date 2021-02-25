package com.zhang.practice.thread;

import org.springframework.util.StringUtils;

import java.util.concurrent.CountDownLatch;

/**
 * @author : zzh
 * create at:  2021/1/24
 * @description:
 *
 *  问题：请实现如下功能：
 * 1. 以字符串的形式给出两个整数，例如"12234237948283472974927349827349837492734"，给出的整数很大，会超出int型的最大长度，称之为大整数
 * 2. 请实现一个方法，输出两个大整数的加和
 * 3. 注意不要使用BigInteger这样的工具类
 *
 */
public class Count {

    public String sum(String a, String b) {
        if (StringUtils.isEmpty(a) || StringUtils.isEmpty(b)) {
            throw new ArithmeticException();
        }
        int lenA = a.length();
        int lenB = b.length();
        int flag = 0;
        int diff;
        if (lenA > lenB) {
            diff = lenA - lenB;
            b = padding(b, diff);
        }else {
            diff = lenB - lenA;
            a = padding(a, diff);
        }
        StringBuilder result = new StringBuilder();
        for (int i = lenA - 1; i >= 0; i--) {
            int i1 = a.charAt(i) - '0';
            int i2 = b.charAt(i) - '0';

            int mod = (i1 + i2 + flag) % 10;
            flag = (i1 + i2 + flag) / 10;
            result.append(mod);
        }
        return result.reverse().toString();
    }






    /**
     * 数据填补
     *
     * @param a
     * @param count
     * @return
     */
    public String padding(String a, int count) {
        StringBuilder sb = new StringBuilder();
        while (count > 0) {
            sb.append("0");
            count--;
        }
        return sb.append(a).toString();
    }


    static class Job implements Runnable {

        private CountDownLatch countDownLatch;

        public Job(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getId());
            countDownLatch.countDown();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        System.out.println("job running");

        CountDownLatch countDownLatch = new CountDownLatch(5);
        new Thread(new Job(countDownLatch)).start();
        new Thread(new Job(countDownLatch)).start();
        new Thread(new Job(countDownLatch)).start();
        new Thread(new Job(countDownLatch)).start();
        new Thread(new Job(countDownLatch)).start();

        countDownLatch.await();
        System.out.println("job finishing");

    }



}
