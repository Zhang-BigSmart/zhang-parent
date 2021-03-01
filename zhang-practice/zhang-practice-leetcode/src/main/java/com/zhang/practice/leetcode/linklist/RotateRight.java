package com.zhang.practice.leetcode.linklist;

/**
 * @author : zzh
 * create at:  2021/2/25
 * @description:
 */
public class RotateRight {

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) {
            return head;
        }
        int len = 1;
        ListNode fast = head, slow = head;
        while (fast.next != null) {
            fast = fast.next;
            len++;
        }
        fast.next = head;

        int step = len - k % len;
        while (step-- > 1) {
            slow = slow.next;
        }
        head = slow.next;
        slow.next = null;
        return head;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
