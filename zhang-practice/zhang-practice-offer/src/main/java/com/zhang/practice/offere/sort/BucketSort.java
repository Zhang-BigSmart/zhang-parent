package com.zhang.practice.offere.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * @author : zzh
 * create at:  2021/2/7
 * @description:
 */
public class BucketSort {

    public void sort(int[] arr) {
        if (arr.length == 0)
            return;
        int max = arr[0];
        int min = arr[0];
        for (int i : arr) {
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
        }
        int bais = max - min;
        int bucketNum = bais / 5 + 1;
        ArrayList<LinkedList<Integer>> bucketList = new ArrayList<>(bucketNum);
        // 初始化桶
        for (int i = 0; i < bucketNum; i++) {
            bucketList.add(new LinkedList<>());
        }
        // 遍历原数组，将每个元素放入桶中
        for (int i = 0; i < arr.length; i++) {
            bucketList.get((arr[i] - min) / bais).add(arr[i] - min);
        }
        // 对桶内的元素进行排序，我这里采用系统自带的排序工具
        for (int i = 0; i < bucketNum; i++) {
            Collections.sort(bucketList.get(i));
        }
        // 把每个桶排序好的数据进行合并汇总放回原数组
        int k = 0;
        for (int i = 0; i < bucketNum; i++) {
            for (Integer t : bucketList.get(i)) {
                arr[k++] = t + min;
            }
        }
    }
}
