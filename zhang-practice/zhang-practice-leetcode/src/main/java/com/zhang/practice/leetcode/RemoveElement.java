package com.zhang.practice.leetcode;


/**
 * @ClassName RemoveElement
 * @Description:
 * Given an array nums and a value val, remove all instances of that value in-place and return the new length.
 *
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 *
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 *
 *
 *
 * @Author: zhangzh
 * @Date 2018/12/9 10:49
 */
public class RemoveElement {

    /**
     * 方法一： 使用两个指针，i(慢指针), j(快指针)
     * @param nums
     * @param val
     * @return
     */
    public static int removeElement(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }

    /**
     * 方法二：方法类似快排，前后指针，左边先开始，右边都是val的值
     * @param nums
     * @param val
     * @return
     */
    public int removeElement2(int[] nums, int val) {
        int i = 0;
        int n = nums.length;
        while (i < n) {
            if (nums[i] == val) {
                nums[i] = nums[n - 1];
                // reduce array size by one
                n--;
            } else {
                i++;
            }
        }
        return n;
    }

    public static void main(String[] args) {
        //int nums[] = {0,1,2,2,3,0,4,2};
        int nums[] = {3,2,2,3};
        System.out.println(removeElement(nums, 3));
    }
}
