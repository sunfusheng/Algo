package com.wangcheng.leetcode.LeetCode.String;

/**
 * 647.回文子串
 * 给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。
 * 回文字符串 是正着读和倒过来读一样的字符串。
 * 子字符串 是字符串中的由连续字符组成的一个序列。
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 * <p>
 * 示例 1：
 * 输入：s = "abc"
 * 输出：3
 * 解释：三个回文子串: "a", "b", "c"
 * <p>
 * 示例 2：
 * 输入：s = "aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 * <p>
 * 提示：
 * 1 <= s.length <= 1000
 * s 由小写英文字母组成
 *
 * @author sunfusheng
 * @since 2022/06/12
 */
public class CountPalindrome {

    /**
     * 中心扩展算法
     * <p>
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     */
    public int countPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            count += count(s, i, i);
            count += count(s, i, i + 1);
        }
        return count;
    }

    private int count(String s, int start, int end) {
        int count = 0;
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            count++;
            start--;
            end++;
        }
        return count;
    }
}
