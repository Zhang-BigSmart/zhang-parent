package com.zhang.practice.offere.sort;

import java.util.Arrays;

/**
 * @ClassName MergeSort
 * @Description:归并排序
 * @Author: zhangzh
 * @Date 2019/2/4 14:25
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] a = {12,35,-1,99,18,76,1,-1,-2};
        MergeSort sort = new MergeSort();
        sort.sort(a);
        System.out.println(Arrays.toString(a));
    }

    public void sort(int[] array) {
        int[] temp = new int[array.length];
        sort(array, 0, array.length-1, temp);
    }

    public void sort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            sort(arr, left, mid, temp);
            sort(arr, mid+1, right, temp);
            merge(arr, left, mid, right, temp);
        }
    }

    public void merge(int[] arr, int left, int mid, int right, int[] temp) {
        // 左序列指针
        int i = left;
        // 右序列指针
        int j = mid + 1;
        // 临时数据指针
        int t = 0;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t++] = arr[i++];
            }else {
                temp[t++] = arr[j++];
            }
        }
        // 将左边剩余元素填充进temp中
        while (i <= mid) {
            temp[t++] = arr[i++];
        }
        // 将右边剩余元素填充进temp中
        while (j <= right) {
            temp[t++] = arr[j++];
        }
        t = 0;
        // 将temp中的元素全部拷贝到原数组中
        while (left <= right) {
            arr[left++] = temp[t++];
        }
    }

}
