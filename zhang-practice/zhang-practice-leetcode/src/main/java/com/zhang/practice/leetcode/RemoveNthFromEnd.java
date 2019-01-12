package com.zhang.practice.leetcode;

import java.util.LinkedList;

/**
 * @ClassName RemoveNthFromEnd
 * @Description:
 * @Author: zhangzh
 * @Date 2018/10/2 14:50
 */
public class RemoveNthFromEnd {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        LinkedList<ListNode> list = new LinkedList<>();
        int length = 0;
        ListNode temp = head;
        while (temp != null) {
            list.add(temp);
            length++;
            temp = temp.next;
        }
        if (length == n) {
            return head.next;
        }
        int pos = length - n;
        temp = head;
        for (int i = 1; i <= length; i++) {
            if (i == pos + 1) {
                list.remove();
            }else {
                temp.next = list.remove();
                temp = temp.next;
                temp.next = null;
            }
        }
        return head;
    }

    /**
     * slow  fast是两个指针，利用两个指针的差位找到 n 的位置
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {

        ListNode start = new ListNode(0);
        ListNode slow = start, fast = start;
        slow.next = head;

        //Move fast in front so that the gap between slow and fast becomes n
        for(int i=1; i<=n+1; i++)   {
            fast = fast.next;
        }
        //Move fast to the end, maintaining the gap
        while(fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        //Skip the desired node
        slow.next = slow.next.next;
        return start.next;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        RemoveNthFromEnd r = new RemoveNthFromEnd();
        ListNode result = r.removeNthFromEnd2(n1, 2);
        System.out.println(result.val);
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        this.val = x;
    }
}
