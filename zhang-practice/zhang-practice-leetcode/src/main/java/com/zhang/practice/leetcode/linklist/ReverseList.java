package com.zhang.practice.leetcode.linklist;

/**
 * @author : zzh
 * create at:  2021/3/1
 * @description:
 *
 * 反转一个单链表。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 *
 * 迭代、递归
 */
public class ReverseList {

    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;
        ListNode temp;
        while (curr != null) {
            // 保存当前节点的下一个节点
            temp = curr.next;
            // 将当前节点指向pre
            curr.next = pre;
            // curr 和 pre 都向前进一位
            pre = curr;
            curr = temp;
        }
        return pre;
    }

    public ListNode reverseList1(ListNode head) {
        //递归终止条件是当前为空，或者下一个节点为空
        if (head == null || head.next == null) {
            return head;
        }
        // 这里的cur就是最后一个节点
        ListNode cur = reverseList1(head.next);
        head.next.next = head;
        head.next = null;
        return cur;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
