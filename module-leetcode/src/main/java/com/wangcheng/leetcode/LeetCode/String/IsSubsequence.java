package com.wangcheng.leetcode.LeetCode.String;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 * 【题目】
 * 392.判断子序列
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 * <p>
 * 你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），
 * 而 s 是个短字符串（长度 <=100）。
 * <p>
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符
 * 相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 * <p>
 * 【示例】
 * 示例 1:
 * s = "abc", t = "ahbgdc"
 * 返回 true.
 * <p>
 * 示例 2:
 * s = "axc", t = "ahbgdc"
 * 返回 false.
 * <p>
 * 【后续挑战】
 * 如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，
 * 你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
 *
 * @author liwangcheng
 * @date 2020/5/7.
 */
public class IsSubsequence {

    /**
     * 如果是匹配一个较短字符串 s ，对于 s 中每一个char 都优先匹配最开始遇到的，直接扫描一遍 t 即可
     */
    public static boolean solution1(String s, String t) {
        int i = 0;
        int tLen = t.length();
        for (char ch : s.toCharArray()) {
            while (i < tLen && t.charAt(i) != ch) {
                i++;
            }
            i++;
        }
        return i <= tLen;
    }

    /**
     * TODO
     * 后续挑战
     * 匹配一串字符需要 $$O(n)$$ ，n 为 t 的长度。如果有大量输入的 S，
     * 称作 S1 , S2 , ... , Sk 其中 k >= 10 亿，你需要依次检查它们
     * 是否为 T 的子序列，这时候处理每一个子串都需要扫描一遍 T 是很费时的。
     * <p>
     * 在这种情况下，需要在匹配前对 T 做预处理，利用一个二维数组记录每个
     * 位置的下一个要匹配的字符的位置，这里的字符是'a' ~ 'z'，所以这个
     * 数组的大小是 dp[n][26]，n 为 T 的长度。那么每处理一个子串只需要
     * 扫描一遍 Si 即可，因为在数组的帮助下对 T 是“跳跃”扫描的。比如下面
     * 匹配 "ada" 的例子，只需要“跳跃”三次。
     * <p>
     * 参考：
     * https://leetcode-cn.com/problems/is-subsequence/solution/javati-jie-he-hou-xu-tiao-zhan-by-lil-q/
     */
    public static boolean solution2(String s, String t) {
        // 预处理
        // 开头加一个空字符作为匹配入口
        t = " " + t;
        int n = t.length();
        // 记录每个位置的下一个ch的位置
        int[][] dp = new int[n][26];
        for (char ch = 0; ch < 26; ch++) {
            int p = -1;
            // 从后往前记录dp
            for (int i = n - 1; i >= 0; i--) {
                dp[i][ch] = p;
                if (t.charAt(i) == ch + 'a') {
                    p = i;
                }
            }
        }
        // 匹配
        int i = 0;
        // 跳跃遍历
        for (char ch : s.toCharArray()) {
            i = dp[i][ch - 'a'];
            if (i == -1) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution1(abc,ahbgdc) = " + solution1("abc", "ahbgdc"));
        LeetCodeUtil.logln("solution1(axc,ahbgdc) = " + solution1("axc", "ahbgdc"));
        LeetCodeUtil.logln("solution2(abc,ahbgdc) = " + solution2("abc", "ahbgdc"));
        LeetCodeUtil.logln("solution2(axc,ahbgdc) = " + solution2("axc", "ahbgdc"));
    }
}
