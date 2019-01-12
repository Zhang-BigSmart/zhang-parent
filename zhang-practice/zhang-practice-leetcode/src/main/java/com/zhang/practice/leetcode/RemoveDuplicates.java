package com.zhang.practice.leetcode;


import java.util.Arrays;

/**
 * @ClassName RemoveDuplicates
 * @Description:
 *
 * Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.
 *
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory
 * @Author: zhangzh
 * @Date 2018/12/6 17:17
 */
public class RemoveDuplicates {

    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];
            }
        }
        System.out.println(Arrays.toString(nums));
        return i;
    }

    public static void main(String[] args) {
        int nums[] = {1,1,1,2};
        System.out.println(removeDuplicates(nums));


    }
}
