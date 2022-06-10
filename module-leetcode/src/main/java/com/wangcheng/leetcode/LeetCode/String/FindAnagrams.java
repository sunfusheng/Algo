package com.wangcheng.leetcode.LeetCode.String;

import java.util.ArrayList;
import java.util.List;

/**
 * 438.找到字符串中所有字母异位词
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 * <p>
 * 示例 1:
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 * <p>
 * 示例 2:
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 * <p>
 * 提示:
 * 1 <= s.length, p.length <= 3 * 104
 * s 和 p 仅包含小写字母
 *
 * @author sunfusheng
 * @since 2022/06/10
 */
public class FindAnagrams {

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s.length() < p.length()) {
            return res;
        }
        int[] counts = new int[26];
        for (int i = 0; i < p.length(); i++) {
            counts[p.charAt(i) - 'a']++;
            counts[s.charAt(i) - 'a']--;
        }
        if (areAllZero(counts)) {
            res.add(0);
        }
        for (int i = p.length(); i < s.length(); i++) {
            counts[s.charAt(i) - 'a']--;
            counts[s.charAt(i - p.length()) - 'a']++;
            if (areAllZero(counts)) {
                res.add(i - p.length() + 1);
            }
        }
        return res;
    }

    private boolean areAllZero(int[] counts) {
        for (int count : counts) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }
}
