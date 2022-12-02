package com.zhang.practice.thread;

/**
 * @author : zzh
 * create at:  2022/5/17
 * @description:
 * 行列都是有序递增的二维整数数组，查找整数k是否存在, 实现的算法时间复杂度是多少？
 *
 * 例如：
 * 1 3 5 7 9
 * 3 5 7 9 11
 * 4 6 8 10 12
 *
 *
 */
public class Demo {

    public boolean demo(int[][] array, int target) {
        int n = array.length - 1;
        int m = array[0].length;

        int left = 0;
        while (left < n) {
            int mid = (left + n) / 2;
            if (array[mid][0] == target) {
                return true;
            }
            // 如果target>array[mid][0]，就需要从前面一排一排遍历

        }
        return false;
    }

}
