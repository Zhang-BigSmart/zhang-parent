package com.zhang.practice.leetcode;

import java.util.Stack;

/**
 * @ClassName ValidParentheses
 * @Description:
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * An input string is valid if:
 *
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Note that an empty string is also considered valid.
 *
 * Example 1:
 *
 * Input: "()"
 * Output: true
 * @Author: zhangzh
 * @Date 2018/10/5 22:24
 */
public class ValidParentheses {


    /*public boolean isValid1(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (char c : s.toCharArray()) {
            if (c == '(')
                stack.push(')');
            else if (c == '{')
                    stack.push('}');
                else if (c == '[')
                    stack.push(']');
                else if (stack.isEmpty() || stack.pop() != c)
                    return false;

            return stack.isEmpty();
        }
    }*/

    /**
     * 匹配后就出栈
     * @param s
     * @return
     */
    public boolean isValid3(String s) {
        Stack<Character> stack = new Stack<>();
        for(char c : s.toCharArray()){
            switch(c){
                case ']': if(stack.isEmpty() || stack.pop()!='[') return false; break;
                case ')': if(stack.isEmpty() || stack.pop()!='(') return false; break;
                case '}': if(stack.isEmpty() || stack.pop()!='{') return false; break;
                default: stack.push(c);
            }
        }
        return stack.isEmpty();
    }

}
