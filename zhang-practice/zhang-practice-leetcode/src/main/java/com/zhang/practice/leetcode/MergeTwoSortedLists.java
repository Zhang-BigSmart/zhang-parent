package com.zhang.practice.leetcode;

/**
 * @ClassName MergeTwoSortedLists
 * @Description:
 * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
 * Example:
 *
 * Input: 1->2->4, 1->3->4
 * Output: 1->1->2->3->4->4
 * @Author: zhangzh
 * @Date 2018/10/14 12:12
 */
public class MergeTwoSortedLists {

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return val + "";
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        ListNode temp = l1;
        ListNode pre = l1;
        if (l1.val > l2.val) {
           temp = l2;
           l2 = l1;
           l1 = temp;
        }
        while(temp != null && l2 != null){
            if (l2.val > temp.val) {
                pre = temp;
                if (temp.next == null) {
                    temp.next = l2;
                    break;
                }
                temp = temp.next;
                continue;
            }
            ListNode swap = pre.next;
            pre.next = l2;
            l2 = l2.next;
            pre.next.next = swap;
            pre = pre.next;
        }
        return l1;
    }


    public ListNode mergeTwoLists2(ListNode l1, ListNode l2){
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        if(l1.val < l2.val){
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else{
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    public static void main(String[] args) {
        MergeTwoSortedLists m = new MergeTwoSortedLists();
        ListNode n1_1 = m.new ListNode(2);
//        ListNode n1_2 = m.new ListNode(2);
//        ListNode n1_3 = m.new ListNode(4);
//        n1_1.next = n1_2;
//        n1_2.next = n1_3;

        ListNode n2_1 = m.new ListNode(1);
//        ListNode n2_2 = m.new ListNode(3);
//        ListNode n2_3 = m.new ListNode(4);
//        n2_1.next = n2_2;
//        n2_2.next = n2_3;

        ListNode l = m.mergeTwoLists(n1_1, n2_1);
        while (l != null) {
            System.out.println(l);
            l = l.next;
        }
    }



}





