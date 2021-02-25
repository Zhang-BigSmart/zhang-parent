package com.zhang.practice.offere.sort;

import java.util.Arrays;

/**
 * @ClassName SelectSort
 * @Description:选择排序
 * @Author: zhangzh
 * @Date 2019/2/2 8:50
 */
public class SelectSort {

    public void sort(int[] arrays) {
        if (arrays == null) {
            return;
        }
        int length = arrays.length;
        for (int i = 0; i < length - 1; i++) {
            int pos = i;
            for (int j = pos + 1; j < length; j++) {
                if (arrays[pos] > arrays[j]) {
                    pos = j;
                }
            }
            if (pos != i) {
                int temp = arrays[pos];
                arrays[pos] = arrays[i];
                arrays[i] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {12, 35, 99, 18, 76};
        SelectSort sort = new SelectSort();
        sort.sort(a);
        System.out.println(Arrays.toString(a));
    }
}
