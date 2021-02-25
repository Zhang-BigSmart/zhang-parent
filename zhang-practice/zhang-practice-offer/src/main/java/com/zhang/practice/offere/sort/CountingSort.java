package com.zhang.practice.offere.sort;

import java.util.Arrays;

/**
 * @author : zzh
 * create at:  2021/2/7
 * @description:
 */
public class CountingSort {

    public static void sort(int[] array) {
        // 获取最大值和最小值
        int max = array[0];
        int min = array[0];
        for (int i : array) {
            max = Math.max(max, i);
            min = Math.min(min, i);
        }
        int[] bucket = new int[max - min + 1];
        for (int i : array) {
            bucket[i - min]++;
        }
        int index = 0;
        for (int i = 0; i < bucket.length; i++) {
            int num = bucket[i];
            while (num > 0) {
                array[index++] = i + min;
                num--;
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {12,35,99,18,76};
        CountingSort.sort(a);
        System.out.println(Arrays.toString(a));
    }
}
