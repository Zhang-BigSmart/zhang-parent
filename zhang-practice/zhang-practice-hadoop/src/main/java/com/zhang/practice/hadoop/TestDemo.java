package com.zhang.practice.hadoop;

/**
 * @author : zzh
 * create at:  2020/1/19
 * @description:
 */
public class TestDemo {

    public String test() {
        String s = "success";
        try{
            throw new RuntimeException();
        }finally {
            System.out.println("final");
        }
    }

    public static void main(String[] args) {
        TestDemo testDemo = new TestDemo();
        testDemo.test();

    }
}
