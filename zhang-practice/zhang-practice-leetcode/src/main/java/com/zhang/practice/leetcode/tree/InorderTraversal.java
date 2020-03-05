package com.zhang.practice.leetcode.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author : zzh
 * create at:  2020/2/12
 * @description:
 * Approach 1: Recursive Approach
 * this is what i use
 *
 *
 * Time complexity : O(n).
 * The time complexity is O(n) because the recursive function is T(n)=2⋅T(n/2)+1
 *
 * Space complexity : The worst case space required is O(n), and in the average case it's O(logn) where n is number of nodes
 *
 *
 * Approach 2: Iterating method using Stack
 *
 * Time complexity : O(n).
 *
 * Space complexity : O(n).
 *
 *
 *
 *
 */
public class InorderTraversal {

    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> list = new ArrayList();
        cal(root, list);
        return list;
    }

    public void cal(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }

        if (node.left != null) {
            cal(node.left, list);
        }
        list.add(node.val);
        if (node.right != null) {
            cal(node.right, list);
        }
        return;
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);

        n1.right = n2;
        n2.left = n3;

        InorderTraversal a = new InorderTraversal();
        List list = a.inorderTraversal1(n1);
        System.out.println(Arrays.asList(list));
    }


    public List < Integer > inorderTraversal2(TreeNode root) {
        List < Integer > res = new ArrayList < > ();
        Stack< TreeNode > stack = new Stack < > ();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }
        return res;
    }

}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}


