package com.zhang.practice.leetcode;

import java.util.Arrays;

/**
 * @ClassName RemoveElement
 * @Description:
 * Given an array nums and a value val, remove all instances of that value in-place and return the new length.
 *
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 *
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 * @Author: zhangzh
 * @Date 2018/12/9 10:49
 */
public class RemoveElement {

    public static int removeElement(int[] nums, int val) {
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        System.out.println(Arrays.toString(nums));
        return i+1;
    }

    public static void main(String[] args) {
        int nums[] = {0,1,2,2,3,0,4,2};
        System.out.println(removeElement(nums, 2));
    }
}
