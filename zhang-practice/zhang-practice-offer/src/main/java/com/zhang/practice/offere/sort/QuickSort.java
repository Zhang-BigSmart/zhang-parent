package com.zhang.practice.offere.sort;

import java.util.Arrays;

/**
 * @author : zzh
 * create at:  2021/2/4
 * @description:快速排序
 */
public class QuickSort {

    public void sort(int[] a, int left, int right) {
        if (left > right)
            return;
        int t;
        int temp = a[left];
        int i = left;
        int j = right;
        while (i != j) {
            // 右边第一个小于temp的数
            while (a[j] >= temp && i < j) {
                j--;
            }
            // 左边第一个大于temp的数
            while (a[i] <= temp && i < j) {
                i++;
            }
            if (i < j) {
                t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }
        a[left] = a[i];
        a[i] = temp;
        sort(a, left, i - 1);
        sort(a, i + 1, right);
    }

    public static void main(String[] args) {
        int[] a = {12,35,99,18,76};
        QuickSort quickSort = new QuickSort();
        quickSort.sort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }
}
