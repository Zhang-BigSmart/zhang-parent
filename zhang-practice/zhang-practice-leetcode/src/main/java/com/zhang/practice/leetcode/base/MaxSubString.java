package com.zhang.practice.leetcode.base;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName MaxSubString
 * @Description:
 * @Author: zhangzh
 * @Date 2019/3/7 15:55
 */
public class MaxSubString {

    public String solve(String s1, String s2) {
        Integer max;
        String bigger = s1;
        if (s1.length() < s2.length()) {
            bigger = s2;
            s1 = s2;
            s2 = bigger;
        }
        for (int i = 0; i < s2.length(); i++) {
            for (int j = 0; j < s1.length(); j++) {
                if (s2.charAt(i) == s1.charAt(j)) {

                }
            }
        }
        return null;
    }
}
