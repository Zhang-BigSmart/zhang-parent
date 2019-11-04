package com.zhang.practice.leetcode;

import java.util.Arrays;

/**
 * @author : zzh
 * create at:  2019/10/28
 * @description:
 *
 * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
 *
 * Your algorithm's runtime complexity must be in the order of O(log n).
 *
 * If the target is not found in the array, return [-1, -1].
 *
 * Example 1:
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 *
 * Example 2:
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 */
public class SearchRange {

    public static void main(String[] args) {
        int a[] = {5,7,7,8,8,10};
        int target = 8;
//        int a[] = {1};
//        int target = 1;

        System.out.println(Arrays.toString(SearchRange.find(0, a.length - 1, target, a)));
    }

    public static int[] find(int low, int high, int target, int a[]) {
        if (high >= low) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > target) {
                return find(low,mid-1, target, a);
            }
            else if (a[mid] < target) {
                return find(mid+1, high, target, a);
            }
            else {
                int left = mid;
                int right = mid;
                while (left > low) {
                    if (left - 1 >= low && a[left-1] == target) {
                        left--;
                    }else {
                        break;
                    }
                }
                while (right < high) {
                    if (right + 1 <= high && a[right+1] == target) {
                        right++;
                    }else {
                        break;
                    }
                }
                return new int[]{left, right};
            }
        }else {
            return new int[]{-1, -1};
        }
    }

}
