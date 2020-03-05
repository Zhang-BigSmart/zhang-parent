package com.zhang.practice.leetcode.tree;

import java.util.*;

/**
 * @author : zzh
 * create at:  2020/2/22
 * @description:
 */
public class LevelOrderTraversal {

    public List<List<Integer>> levelOrder(TreeNode root) {
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
                while (!l.isEmpty()) {
                    TreeNode curNode = l.poll();
                    curList.add(curNode.val);
                    if (curNode.left != null) newList.add(curNode.left);
                    if (curNode.right != null) newList.add(curNode.right);
                }
                list.add(curList);
            }
            if (newList.size() > 0) listQueue.add(newList);
        }
        return list;
    }

    public static void main(String[] args) {
        TreeNode t1 = new LevelOrderTraversal.TreeNode(5);
        TreeNode t2 = new LevelOrderTraversal.TreeNode(10);
        TreeNode t3 = new LevelOrderTraversal.TreeNode(15);
        TreeNode t4 = new LevelOrderTraversal.TreeNode(12);
        TreeNode t5 = new LevelOrderTraversal.TreeNode(20);

        t2.left = t1;
        t2.right = t3;

        t3.left = t4;
        t3.right = t5;

        /*LevelOrderTraversal.TreeNode t1 = new LevelOrderTraversal.TreeNode(1);
        LevelOrderTraversal.TreeNode t2 = new LevelOrderTraversal.TreeNode(2);
        LevelOrderTraversal.TreeNode t3 = new LevelOrderTraversal.TreeNode(3);
        t2.left = t1;
        t2.right = t3;*/

        LevelOrderTraversal v = new LevelOrderTraversal();
        List<List<Integer>> list = v.levelOrder(t2);
        for (List<Integer> integers : list) {
            System.out.println(Arrays.toString(integers.toArray()));
        }
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
