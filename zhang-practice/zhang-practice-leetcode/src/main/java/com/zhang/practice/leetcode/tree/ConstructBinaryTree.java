package com.zhang.practice.leetcode.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author : zzh
 * create at:  2020/2/27
 * @description:
 */
public class ConstructBinaryTree {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0) {
            return null;
        }
        List<Integer> preList = new ArrayList();
        List<Integer> inList = new ArrayList();
        for(int p : preorder) {
            preList.add(p);
        }
        for(int i : inorder) {
            inList.add(i);
        }
        return solution(preList, inList);
    }

    public TreeNode solution(List<Integer> preList, List<Integer> inList) {
        if (inList.size() == 1) {
            return new TreeNode(inList.get(0));
        }
        if (preList.size() == 0) {
            return null;
        }
        int headVal = preList.get(0);
        TreeNode node = new TreeNode(headVal);
        int headValPos = inList.indexOf(headVal);
        List<Integer> inLeftList = inList.subList(0, headValPos);
        List<Integer> preLeftList = preList.subList(1,inLeftList.size() + 1);
        if (!inLeftList.isEmpty() && !preLeftList.isEmpty()) {
            node.left = solution(preLeftList, inLeftList);
        }

        if (headValPos + 1 < inList.size()) {
            List<Integer> inRightList = inList.subList(headValPos + 1, inList.size());
            List<Integer> preRightList = preList.subList(preLeftList.size() + 1, preList.size());
            node.right = solution(preRightList, inRightList);
        }
        return node;
    }

    public TreeNode solution2(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
        if (preStart > preorder.length - 1 || inStart > inEnd) {
            return null;
        }

        TreeNode node = new TreeNode(preorder[preStart]);
        int inIndex = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == node.val) {
                inIndex = i;
            }
        }
        node.left = solution2(preStart + 1, inStart, inIndex - 1, preorder, inorder);
        node.right = solution2(preStart + inIndex + 1 - inStart, inIndex + 1, inEnd, preorder, inorder);
        return node;

    }



    public static void main(String[] args) {
        int[] pre = {3,9,20,15,7};
        int[] in = {9,3,15,20,7};
        ConstructBinaryTree demo = new ConstructBinaryTree();

        //TreeNode t = demo.solution(Arrays.asList(pre), Arrays.asList(in));
//        TreeNode t = demo.buildTree(pre, in);
        TreeNode t = demo.solution2(0, 0, in.length - 1, pre, in);
        System.out.println(t.val);


    }

    static class TreeNode {
        int val;
        ConstructBinaryTree.TreeNode left;
        ConstructBinaryTree.TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
