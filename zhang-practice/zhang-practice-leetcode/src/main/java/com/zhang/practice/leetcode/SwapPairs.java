package com.zhang.practice.leetcode;

/**
 * @ClassName SwapPairs
 * @Description:
 *
 * Given a linked list, swap every two adjacent nodes and return its head.
 *
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 *
 *
 *
 * Example:
 *
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 *
 * @Author: zhangzh
 * @Date 2019/6/2 16:05
 */
public class SwapPairs {

    class ListNode{
        int val;
        ListNode next;
        ListNode(int x) {
            this.val = x;
        }
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode n = head.next;
        head.next = swapPairs(head.next.next);
        n.next = head;
        return n;
    }

    public static void main(String[] args) {
        SwapPairs s = new SwapPairs();
        ListNode l1 = s.new ListNode(1);
        ListNode l2 = s.new ListNode(2);
        ListNode l3 = s.new ListNode(3);
        ListNode l4 = s.new ListNode(4);

        l1.next = l2;
        l2.next = l3;
        l3.next = l4;

        s.swapPairs(l1);
    }
}
