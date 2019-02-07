package com.zhang.practice.offere.sort;

import java.util.Arrays;

/**
 * @ClassName InsertSort
 * @Description:插入排序
 * @Author: zhangzh
 * @Date 2019/1/29 9:18
 */
public class InsertSort {

    public void sort(int[] arrays) {
        int length = arrays.length;
        int i, j, temp;
        for (i = 0; i < length-1; i++) {
            j = i;
            temp = arrays[i+1];
            while (j > -1 && temp < arrays[j]){
                arrays[j+1] = arrays[j];
                j--;
            }
            arrays[j+1] = temp;
        }
    }

    public static void main(String[] args) {
        int[] a = {12,35,-1,99,18,76,1,-1,-2};
        InsertSort insertSort = new InsertSort();
        insertSort.sort(a);
        System.out.println(Arrays.toString(a));
    }
}
