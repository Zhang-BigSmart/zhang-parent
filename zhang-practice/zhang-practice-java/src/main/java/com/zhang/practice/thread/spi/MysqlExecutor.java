package com.zhang.practice.thread.spi;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : zzh
 * create at:  2021/10/3
 * @description:
 */
public class MysqlExecutor implements Executor {
    @Override
    public String invoker() {
        return "mysql";
    }

    public static void main(String[] args) {

        List l = new ArrayList();
        l.forEach(a -> System.out.println(a));

    }
}
