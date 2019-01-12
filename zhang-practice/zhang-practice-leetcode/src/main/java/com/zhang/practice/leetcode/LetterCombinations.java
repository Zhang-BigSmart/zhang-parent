package com.zhang.practice.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName LetterCombinations
 * @Description:
 * @Author: zhangzh
 * @Date 2018/10/2 14:01
 */
public class LetterCombinations {

    /**
     * 1.使用LinkedList存储结果，实现FIFO(先进先出队列)
     *     remove()移除队列头元素
     * 2.第一层循环，遍历digitis
     * 3.第二层循环，获取队列头元素 注：头元素的length == i
     * 4.队列头元素出队，与digits的对应数据内容拼接，再入队
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        LinkedList<String> ans = new LinkedList<>();
        if (digits.isEmpty()) {
            return ans;
        }
        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        for (int i = 0; i < digits.length(); i++) {
            int x = Character.getNumericValue(digits.charAt(i));
            while (ans.peek().length() == i) {
                String t = ans.remove();
                for (char s : mapping[x].toCharArray()) {
                    ans.add(t + s);
                }
            }
        }
        return ans;
    }

    public List<String> letterCombinations2(String digits) {
        LinkedList<String> ans = new LinkedList<>();
        if (digits.isEmpty()) {
            return ans;
        }
        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        while (ans.peek().length() != digits.length()) {
            String remove = ans.remove();
            String map = mapping[digits.charAt(remove.length()) - '0'];
            for (char c : map.toCharArray()) {
                ans.addLast(remove + c);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "23";
        LetterCombinations letterCombinations = new LetterCombinations();
        letterCombinations.letterCombinations(s);
    }
}
