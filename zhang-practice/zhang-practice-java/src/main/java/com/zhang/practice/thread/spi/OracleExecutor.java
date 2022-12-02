package com.zhang.practice.thread.spi;

import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * @author : zzh
 * create at:  2021/10/3
 * @description:
 */
public class OracleExecutor implements Executor{
    @Override
    public String invoker() {
        return "oracle";
    }

    public static void main(String[] args) {
        HashSet<String> set = new HashSet<>();
        set.add("099a");
        set.add("b");
        set.add("0c");

        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }


    }
}


