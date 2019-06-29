package com.zhang.practice.leetcode;

/**
 * @ClassName NextPermutation
 * @Description:
 *
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 *
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 *
 * The replacement must be in-place and use only constant extra memory.
 *
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 *
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 *
 *
 * https://www.cnblogs.com/eudiwffe/p/6260699.html
 *
 *具体方法为：
 *
 * a）从后向前查找第一个相邻元素对(i,j)，并且满足A[i] < A[j]。
 *
 * 易知，此时从j到end必然是降序。可以用反证法证明，请自行证明。
 *
 * b）在[j,end)中寻找一个最小的k使其满足A[i]<A[k]。
 *
 * 由于[j,end)是降序的，所以必然存在一个k满足上面条件；并且可以从后向前查找第一个满足A[i]<A[k]关系的k，此时的k必是待找的k。
 *
 * c）将i与k交换。
 *
 * 此时，i处变成比i大的最小元素，因为下一个全排列必须是与当前排列按照升序排序相邻的排列，故选择最小的元素替代i。
 *
 * 易知，交换后的[j,end)仍然满足降序排序。因为在(k,end)中必然小于i，在[j,k)中必然大于k，并且大于i。
 *
 * d）逆置[j,end)
 *
 * 由于此时[j,end)是降序的，故将其逆置。最终获得下一全排序。
 *
 * 注意：如果在步骤a)找不到符合的相邻元素对，即此时i=begin，则说明当前[begin,end)为一个降序顺序，即无下一个全排列，STL的方法是将其逆置成升序。
 *
 * @Author: zhangzh
 * @Date 2019/6/27 10:01
 */
public class NextPermutation {

    public void nextPermutation(int[] nums) {
        int length = nums.length;
        if (nums.length < 2) return;
        int index = length - 1;
        while (index > 0) {
            if (nums[index-1] < nums[index])
                break;
            index--;
        }
        if (index == 0) {
            reverseSort(nums, 0, length - 1);
            return;
        }else {
            int val = nums[index - 1];
            int j = length - 1;
            while (j >= index) {
                if (nums[j] > val) break;
                j--;
            }
            swap(nums, j, index - 1);
            reverseSort(nums, index, length-1);
            return;
        }
    }

    public void swap(int[] num, int i, int j) {
        int temp;
        temp = num[i];
        num[i] = num[j];
        num[j] = temp;
    }

    public void reverseSort(int[] num, int start, int end) {
        if (start > end) return;
        for (int i = start; i <= (start + end) / 2; i++)
            swap(num, i, start+end-i);
    }

    public static void main(String[] args) {
        NextPermutation next = new NextPermutation();
        int nums[] = {1,2,3};
        next.nextPermutation(nums);
    }
}
