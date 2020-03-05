package com.zhang.practice.leetcode.tree;

import java.util.*;

/**
 * @author : zzh
 * create at:  2020/2/22
 * @description:
 */
public class ZigZagLevelOrderTraversal {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        int order = 1;
        Queue<Queue<TreeNode>> listQueue = new LinkedList();
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) return list;
        Queue<TreeNode> n = new LinkedList<>();
        n.add(root);
        listQueue.add(n);

        while (!listQueue.isEmpty()) {
            Queue<TreeNode> l = listQueue.poll();
            Queue<TreeNode> newList = new LinkedList<>();
            if (l.size() > 0) {
                List<Integer> curList = new ArrayList<>();
                Queue<TreeNode> r = new LinkedList<>(l);
                while (!l.isEmpty()) {
                    TreeNode curNode = l.poll();
                    curList.add(curNode.val);
                }
                list.add(curList);
                r = invert(r);
                while (!r.isEmpty()) {
                    TreeNode curNode = r.poll();
                    if (order == -1) {
                        if (curNode.left != null) newList.add(curNode.left);
                        if (curNode.right != null) newList.add(curNode.right);
                    }else {
                        if (curNode.right != null) newList.add(curNode.right);
                        if (curNode.left != null) newList.add(curNode.left);
                    }
                }
            }
            order*=-1;
            if (newList.size() > 0) listQueue.add(newList);
        }
        return list;
    }

    public Queue<TreeNode> invert(Queue<TreeNode> queue) {
        Queue<TreeNode> temp = new LinkedList();
        Stack<TreeNode> stack = new Stack<>();
        while (!queue.isEmpty()) {
            stack.push(queue.poll());
        }
        while (!stack.empty()) {
            temp.add(stack.pop());
        }
        return temp;
    }


    public static void main(String[] args) {
        TreeNode t1 = new ZigZagLevelOrderTraversal.TreeNode(2);
        TreeNode t2 = new ZigZagLevelOrderTraversal.TreeNode(0);
        TreeNode t3 = new ZigZagLevelOrderTraversal.TreeNode(4);
        TreeNode t4 = new ZigZagLevelOrderTraversal.TreeNode(1);
        TreeNode t5 = new ZigZagLevelOrderTraversal.TreeNode(3);
        TreeNode t6 = new ZigZagLevelOrderTraversal.TreeNode(-1);
        TreeNode t7 = new ZigZagLevelOrderTraversal.TreeNode(5);
        TreeNode t8 = new ZigZagLevelOrderTraversal.TreeNode(1);
        TreeNode t9 = new ZigZagLevelOrderTraversal.TreeNode(6);
        TreeNode t10 = new ZigZagLevelOrderTraversal.TreeNode(8);

        t2.left = t1;
        t2.right = t3;

        t1.left = t4;
        t3.left = t5;
        t3.right = t6;

        t4.left = t7;
        t4.right = t8;

        t5.right = t9;
        t6.right = t10;

        /*LevelOrderTraversal.TreeNode t1 = new LevelOrderTraversal.TreeNode(1);
        LevelOrderTraversal.TreeNode t2 = new LevelOrderTraversal.TreeNode(2);
        LevelOrderTraversal.TreeNode t3 = new LevelOrderTraversal.TreeNode(3);
        t2.left = t1;
        t2.right = t3;*/

        ZigZagLevelOrderTraversal v = new ZigZagLevelOrderTraversal();
        List<List<Integer>> list = v.zigzagLevelOrder(t2);
        for (List<Integer> integers : list) {
            System.out.println(Arrays.toString(integers.toArray()));
        }

        List<Integer> aL = new ArrayList<>();
        aL.add(1);
        aL.add(2);
        aL.add(3);
        aL.add(1, 0);
        System.out.println(Arrays.toString(aL.toArray()));

        List<Integer> lL = new ArrayList<>();
        lL.add(1);
        lL.add(2);
        lL.add(3);
        lL.add(0, 0);
        System.out.println(Arrays.toString(lL.toArray()));

    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
