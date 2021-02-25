package com.zhang.practice.leetcode.base;

/**
 * @author : zzh
 * create at:  2021/2/13
 * @description:
 */
public class MaxArea {

    public int solution(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int max = 0;
        while (left < right) {
            int area = Math.min(height[left], height[right]) * (right - left);
            max = Math.max(max, area);
            if (height[left] <= height[right]) {
                left++;
            }else {
                right--;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] a = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(new MaxArea().solution(a));
    }
}
