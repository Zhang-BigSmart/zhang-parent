package com.zhang.practice.leetcode;

/**
 * @ClassName MostContainer
 * @Description:
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 * @Author: zhangzh
 * @Date 2019/2/25 10:51
 */
public class MostContainer {

    public int maxArea1(int[] height) {
        long startTime = System.nanoTime();
        int maxArea = 0;
        for (int i = height.length - 1; i > 0; i--) {
            /*while ((height[i-1] - height[i] > 1) && i > 1) {
                i--;
                continue;
            }*/
            for (int j = i - 1; j >= 0; j--) {
                int maxHeight = Math.min(height[i], height[j]);
                int area = (i - j)*maxHeight;
                if (area > maxArea) {
                    maxArea = area;
                }
            }
        }
        long endTime = System.nanoTime();
        System.out.println("method1 use time:" + (endTime - startTime));
        return maxArea;
    }


    public int maxArea2(int[] height) {
        long startTime = System.nanoTime();
        int left = 0, right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            maxArea = Math.max(maxArea, Math.min(height[left], height[right])
                    * (right - left));
            if (height[left] < height[right])
                left++;
            else
                right--;
        }
        long endTime = System.nanoTime();
        System.out.println("method2 use time:" + (endTime - startTime));
        return maxArea;
    }

    public static void main(String[] args) {
//        int[] array = {1,8,6,2,5,4,8,3,7};
        int[] array = {6,4,3,1,4,6,99,62,1,2,6};
        MostContainer m = new MostContainer();
        System.out.println(m.maxArea1(array));
        System.out.println(m.maxArea2(array));
    }
}
