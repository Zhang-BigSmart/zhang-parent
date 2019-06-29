package com.zhang.practice.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName GenerateParenthesis
 * @Description:
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * For example, given n = 3, a solution set is:
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 *
 * https://www.youtube.com/watch?v=sz1qaKt0KGQ
 * @Author: zhangzh
 * @Date 2019/5/26 12:17
 */
public class GenerateParenthesis {

    public List<String> generateParenthesis(int n) {
        List<String> combinations = new ArrayList<>();
        //generateAll(new char[2 * n], 0, combinations);
        backtrack(combinations, "", 0, 0, n);
        return combinations;
    }

    public void generateAll(char[] current, int pos, List<String> result) {
        if (pos == current.length) {
            if (valid(current)) result.add(new String(current));
        }else {
            current[pos] = '(';
            generateAll(current, pos + 1, result);
            current[pos] = ')';
            generateAll(current, pos + 1, result);
        }
    }

    public boolean valid(char[] current) {
        int balance = 0;
        for (char c : current) {
            if (c == '(') balance++;
            else balance--;
            if (balance < 0) return false;
        }
        return balance== 0;
    }

    public void backtrack(List<String> list, String str, int open, int close, int max) {
        if (str.length() == max * 2){
            list.add(str);
            return;
        }
        if (open < max) {
            backtrack(list, str + "(", open + 1, close, max);
        }
        if (close < open) {
            backtrack(list, str + ")", open, close + 1, max);
        }
    }

    public static void main(String[] args) {
        List<String> list = new GenerateParenthesis().generateParenthesis(3);
        System.out.println(Arrays.toString(list.toArray()));
    }
}
