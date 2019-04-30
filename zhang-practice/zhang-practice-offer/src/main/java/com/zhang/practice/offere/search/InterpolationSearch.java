package com.zhang.practice.offere.search;

/**
 * @ClassName InterpolationSearch
 * @Description:
 * Interpolation search algorithm implementation
 *
 * Worst-case performance	 O(n)
 * Best-case performance	O(1)
 * Average performance	O(log(log(n))) if the elements are  uniformly distributed if not O(n)
 * Worst-case space complexity	O(1)
 *
 * @Author: zhangzh
 * @Date 2019/2/19 9:38
 */
public class InterpolationSearch {

    public int search(int[] array, int key, int left, int right) {
        if (left > right) return -1;
        int mid = left + ((key - array[left] * (right - left) / (array[right] - array[left])));
        if (array[mid] == key) {
            return mid;
        }else if (array[mid] > key) {
            return search(array, key, left, mid - 1);
        }else {
            return search(array, key, mid + 1, right);
        }
    }
}
