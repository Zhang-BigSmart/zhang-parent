package com.zhang.practice.thread;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : zzh
 * create at:  2019/12/29
 * @description:
 */
public class MapTest {

    public static void main(String[] args) {

        Map<Long, String> map = new HashMap<>();
        map.put(1L, "old");

        System.out.println(map.put(1L, "new"));
    }
}
