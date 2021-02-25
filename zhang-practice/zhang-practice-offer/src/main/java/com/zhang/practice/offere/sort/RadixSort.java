package com.zhang.practice.offere.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author : zzh
 * create at:  2021/2/13
 * @description:基数排序
 */
public class RadixSort {

    public static int[] RadixSort(int[] array) {
        if (array == null || array.length < 2) {
            return array;
        }
        // 1.获取最大数
        int max = array[0];
        for (int i : array) {
            max = Math.max(max, i);
        }
        // 2.获取最大数的位数
        int maxDigit = 0;
        while (max != 0) {
            max /= 10;
            maxDigit++;
        }
        int mod = 10, div = 1;
        ArrayList<ArrayList<Integer>> bucketList = new ArrayList<>();
        // 初始化桶
        for (int i = 0; i < 10; i++)
            bucketList.add(new ArrayList<>());

        for (int i = 0; i < maxDigit; i++, mod *= 10, div *= 10) {
            for (int j = 0; j < array.length; j++) {
                int num = (array[j] % mod) / div;
                bucketList.get(num).add(array[j]);
            }
            int index = 0;
            for (int j = 0; j < bucketList.size(); j++) {
                for (int k = 0; k < bucketList.get(j).size(); k++)
                    array[index++] = bucketList.get(j).get(k);
                bucketList.get(j).clear();
            }
        }
        return array;
    }

    public static void main(String[] args) {
        int[] a = {12,2,131};
        System.out.println(Arrays.toString(RadixSort.RadixSort(a)));
    }
}
