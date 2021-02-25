package com.zhang.practice.leetcode.base;

/**
 * @author : zzh
 * create at:  2021/2/21
 * @description:
 */
public class Trap {

    public int trap(int[] height) {
        int sum = 0;
        int[] max_left = new int[height.length];
        int[] max_right = new int[height.length];
        for (int i = 1; i < height.length - 1; i++) {
            max_left[i] = Math.max(max_left[i], height[i - 1]);
        }
        for (int i = height.length - 2; i >= 0; i--) {
            max_right[i] = Math.max(max_right[i], height[i + 1]);
        }
        for (int i = 1; i < height.length - 1; i++) {
            int min = Math.min(max_left[i], max_right[i]);
            if (min > height[i]) {
                sum = sum + (min - height[i]);
            }
        }
        return sum;
    }

    public int trap2(int[] height) {
        int sum = 0;
        // 左右最大值
        int max_left = 0, max_right = 0;
        // 左右指针
        int left = 1, right = height.length - 2;
        for (int i = 0; i < height.length - 1; i++) {
            if (height[left - 1] < height[right + 1]) {
                max_left = Math.max(height[left - 1], max_left);
                if (max_left > height[left]) {
                    sum += (max_left - height[left]);
                }
                left++;
            }else {
                max_right = Math.max(height[right + 1], max_right);
                if (max_right > height[right]) {
                    sum += (max_right - height[right]);
                }
                right--;
            }
        }
        return sum;
    }
}
