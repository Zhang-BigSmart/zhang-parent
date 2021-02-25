package com.zhang.practice.offere.sort;

import java.util.Arrays;

/**
 * @ClassName BubbleSort
 * @Description:冒泡排序
 * @Author: zhangzh
 * @Date 2019/1/28 9:06
 */
public class BubbleSort {

    public void sort(int[] arrays) {
        if (arrays == null) {
            return;
        }
        int length = arrays.length;
        for (int i = 0; i < length-1; i++) {
            boolean flag = false;
            for (int j = 0; j < length-i-1; j++) {
                if (arrays[j] < arrays[j+1]) {
                    int temp = arrays[j];
                    arrays[j] = arrays[j+1];
                    arrays[j+1] = temp;
                    flag = true;
                }
            }
            if (!flag) break;
        }

    }

    public static void main(String[] args) {
        int[] a = {12,35,99,18,76};
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sort(a);
        System.out.println(Arrays.toString(a));
    }
}
