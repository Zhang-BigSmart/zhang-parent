package com.zhang.practice.leetcode.tree;

import java.util.Stack;

/**
 * @author : zzh
 * create at:  2020/2/21
 * @description:
 */
public class ValidateBinarySearchTree {

    public boolean isValidBST(TreeNode root) {
        return valid(root, root);
    }

    public boolean helper(TreeNode node, Integer lower, Integer upper) {
        if (node == null) return true;

        int val = node.val;
        if (lower != null && val <= lower) return false;
        if (upper != null && val >= upper) return false;

        if (! helper(node.right, val, upper)) return false;
        if (! helper(node.left, lower, val)) return false;
        return true;
    }


    public boolean valid(TreeNode root, TreeNode node) {
        if(node == null) return true;
        //boolean flag = true;
        TreeNode cur = node;


        if (cur.left != null) {
            if (cur.left.val >= cur.val) {
                return false;
            }
            if (cur.val != root.val && (cur.left.val - root.val) * (cur.val - root.val) <= 0) {
                return false;
            }
        }
        if (cur.right != null) {
            if (cur.right.val <= cur.val) {
                return false;
            }
            if (cur.val != root.val && (cur.right.val - root.val) * (cur.val - root.val) <= 0) {
                return false;
            }
        }
        if(!valid(root, cur.left)){
            return false;
        }
        if(!valid(root, cur.right)){
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
//        TreeNode t1 = new ValidateBinarySearchTree.TreeNode(5);
//        TreeNode t2 = new ValidateBinarySearchTree.TreeNode(10);
//        TreeNode t3 = new ValidateBinarySearchTree.TreeNode(15);
//        TreeNode t4 = new ValidateBinarySearchTree.TreeNode(12);
//        TreeNode t5 = new ValidateBinarySearchTree.TreeNode(20);
//
//        t2.left = t1;
//        t2.right = t3;
//
//        t3.left = t4;
//        t3.right = t5;

        TreeNode t1 = new ValidateBinarySearchTree.TreeNode(1);
        TreeNode t2 = new ValidateBinarySearchTree.TreeNode(2);
        TreeNode t3 = new ValidateBinarySearchTree.TreeNode(3);

        t2.left = t1;
        t2.right = t3;


        ValidateBinarySearchTree v = new ValidateBinarySearchTree();
        System.out.println(v.isValidBST(t2));
    }



    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
