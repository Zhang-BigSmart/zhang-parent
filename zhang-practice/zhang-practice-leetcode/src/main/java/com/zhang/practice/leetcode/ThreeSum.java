package com.zhang.practice.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an array nums of n integers, are there elements a, b, c in nums
 * such that a + b + c = 0? Find all unique triplets in the array which
 * gives the sum of zero.
 *
 * note:The solution set must not contain duplicate triplets.
 *
 * Example:
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 *
 * A solution set is:
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 *
 */
public class ThreeSum {

    public List<List<Integer>> threeSum(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> res = new LinkedList<>();
        int len = num.length;
        int left = num[0], right = num[len - 1];
        int leftPos = 0, rightPos = len - 1;
        int leftPos_ = 1, rightPos_ = len - 2;
        for (int i = 0; i < len; i++) {
            if (left + right > 0) {
                while(num[rightPos_] > 0) {
                    if (left + right + num[rightPos_] == 0) {
                        res.add(Arrays.asList(left, right, num[rightPos_]));
                    }else {

                    }
                }
            }
            else if (left + right < 0) {

            }else {

            }
        }
        return res;
    }

    public static void main(String[] args) {
        ThreeSum threeSum = new ThreeSum();
        int[] nums = {-1, -1, 0, 1, 4};
        threeSum.threeSum(nums);
    }

}
