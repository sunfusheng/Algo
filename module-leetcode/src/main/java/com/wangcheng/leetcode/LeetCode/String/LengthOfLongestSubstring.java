package com.wangcheng.leetcode.LeetCode.String;


import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 【题目】
 * 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 【示例】
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * @author liwangcheng
 * @date 2020/3/12.
 */
public class LengthOfLongestSubstring {

    /**
     * 方法一：暴力法
     * PS：题目更新后由于时间限制，会出现 TLE。
     * 思路
     * 逐个检查所有的子字符串，看它是否不含有重复的字符。
     *
     * 时间复杂度：O(n^3)
     * 空间复杂度：O(min(m, n))
     *
     * @param str
     * @return 最大不重复子串长度
     */
    public int solution1(String str) {
        check(str);
        int max = 0;
        for (int i = 0; i < str.length() - 1; i++) {
            for (int j = i + 1; j < str.length(); j++) {
                if (allUnique(str, i, j)) {
                    max = Math.max(max, j - i);
                }
            }
        }
        return max;
    }

    private void check(String str) {
        if (null == str || str.isEmpty()) {
            throw new IllegalArgumentException("reject empty string");
        }
    }

    private boolean allUnique(String str, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            char ch = str.charAt(i);
            if (set.contains(ch)) {
                return false;
            }
            set.add(ch);
        }
        return true;
    }

    /**
     * 方法二：滑动窗口
     *
     * 使用 HashSet 将字符存储在当前窗口 [i, j)（最初 j = i）中。
     * 然后向右侧滑动索引 j，如果它不在 HashSet 中，我们会继续滑动 j。
     * 直到 s[j] 已经存在于 HashSet 中。此时，
     * 找到的没有重复字符的最长子字符串将会以索引 i 开头。
     * 如果我们对所有的 i 这样做，就可以得到答案。
     *
     * 时间复杂度：O(2n) = O(n)，在最糟糕的情况下，每个字符将被 i 和 j 访问两次。
     * 空间复杂度：O(min(m, n))
     *
     * @param str
     * @return 最大不重复子串长度
     */
    public int solution2(String str) {
        check(str);
        Set<Character> set = new HashSet<>();
        int i = 0;
        int j = 0;
        int n = str.length();
        int max = 0;
        while (i < n && j < n) {
            // 尝试移动窗口
            if (set.contains(str.charAt(j))) {
                set.remove(str.charAt(i++));
            } else {
                set.add(str.charAt(j++));
                max = Math.max(max, j - i);
            }
        }
        return max;
    }

    /**
     * 方法三：优化的滑动窗口
     * 思路
     * 如果 s[j] 在 [i, j) 范围内有与 j' 重复的字符，
     * 不需要逐渐增加 i 。可以直接跳过 [i，j']
     * 范围内的所有元素，并将 i 变为 j' + 1。
     *
     * 时间复杂度：O(n)，索引 j 将会迭代 n 次。
     * 空间复杂度（HashMap）：O(min(m, n))，与之前的方法相同。
     *
     * @param str
     * @return 最大不重复子串长度
     */
    public int solution3(String str) {
        check(str);
        int max = 0;
        int n = str.length();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0, j = 0; j < n; j++) {
            char ch = str.charAt(j);
            if (map.containsKey(ch)) {
                i = Math.max(map.get(ch), i);
            }
            max = Math.max(max, j - i);
            map.put(ch, j);
        }
        return max;
    }

    /**
     * 方法四：优化的滑动窗口
     * 思路
     * 假设字符集为 ASCII 128，可以用一个整数数组作为直接访问表来替换 Map
     *
     * 时间复杂度：O(n)，索引 j 将会迭代 n 次。
     * 空间复杂度（Table）：O(m)，m 是字符集的大小。
     *
     * @param str
     * @return
     */
    public int solution4(String str) {
        check(str);
        int max = 0;
        int n = str.length();
        int[] indexMap = new int[128];
        for (int i = 0, j = 0; j < n; j++) {
            char ch = str.charAt(j);
            i = Math.max(indexMap[ch], i);
            max = Math.max(max, j - i);
            indexMap[ch] = j;
        }
        return max;
    }

    public static void main(String[] args) {
        LengthOfLongestSubstring sub = new LengthOfLongestSubstring();
        String testStr = "abcababc";
        LeetCodeUtil.logln(testStr);
        LeetCodeUtil.logln("solution1 - " + sub.solution1(testStr));
        LeetCodeUtil.logln("solution2 - " + sub.solution2(testStr));
        LeetCodeUtil.logln("solution3 - " + sub.solution3(testStr));
        LeetCodeUtil.logln("solution4 - " + sub.solution4(testStr));
        testStr = "bbbbbb";
        LeetCodeUtil.logln(testStr);
        LeetCodeUtil.logln("solution1 - " + sub.solution1(testStr));
        LeetCodeUtil.logln("solution2 - " + sub.solution2(testStr));
        LeetCodeUtil.logln("solution3 - " + sub.solution3(testStr));
        LeetCodeUtil.logln("solution4 - " + sub.solution4(testStr));
    }
}
