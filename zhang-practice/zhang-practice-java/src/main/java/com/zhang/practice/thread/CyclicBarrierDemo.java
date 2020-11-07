package com.zhang.practice.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

    public static void main(String[] args) {
        final int N = 10;
        Thread[] threads = new Thread[N];
        boolean flag = false;
        CyclicBarrier cyclic = new CyclicBarrier(N, new BarrierRun(flag, N));
        System.out.println("开始点名");
        for (int i = 0; i < N; i++) {
            threads[i] = new Thread(new Student(cyclic, i));
            threads[i].start();
        }
    }
}

class Student implements Runnable {

    private int i;

    private final CyclicBarrier cyclic;

    public Student(CyclicBarrier cyclic, int i) {
        this.cyclic = cyclic;
        this.i = i;
    }

    @Override
    public void run() {
        // 等待所有学生到齐
        try {
            System.out.println("学生" + i + " 到");
            cyclic.await();
            doWork();
            cyclic.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    void doWork() {
        System.out.println(this + " 打开书本");
    }

    @Override
    public String toString() {
        return "Student " + i;
    }
}

class BarrierRun implements Runnable {

    boolean flag;
    int N;

    public BarrierRun(boolean flag, int n) {
        this.flag = flag;
        N = n;
    }

    @Override
    public void run() {
        if (flag) {
            System.out.println("老师: 学生" + N + "个，开始上课");
        } else {
            System.out.println("老师: 学生" + N + "个，点名结束");
            flag = true;
        }
    }
}