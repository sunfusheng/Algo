package com.wangcheng.leetcode.LeetCode.String;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 * 【题目】
 * 14. 最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 说明:
 * 所有输入只包含小写字母 a-z 。
 * <p>
 * <p>
 * 【示例】
 * 示例 1:
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * <p>
 * 示例 2:
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 *
 * @author liwangcheng
 * @date 2020/3/18.
 */
public class LongestCommonPrefix {

    /**
     * 方法一：水平扫描法
     * 时间复杂度：O(S)，S 是所有字符串中字符数量的总和。
     * 空间复杂度：O(1)，只需要使用常数级别的额外空间。
     */
    public static String solution1(String[] strs) {
        if (check(strs)) {
            return "";
        }
        String prefix = strs[0];
        for (int i = 0; i < strs.length; i++) {
            while (!strs[i].startsWith(prefix)) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        return prefix;
    }

    private static boolean check(String[] strs) {
        if (strs.length <= 1 || strs[0].isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * 算法二：垂直扫描
     */
    public static String solution2(String[] strs) {
        if (check(strs)) {
            return "";
        }
        for (int i = 0, size = strs[0].length(); i < size; i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i == strs[j].length() || c != strs[j].charAt(i)) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return "";
    }

    /**
     * 算法三：分治
     * 最坏情况下，我们有 n 个长度为 m 的相同字符串。
     * 时间复杂度：O(S)，S 是所有字符串中字符数量的总和，S=m*n。
     * 空间复杂度：O(m⋅log(n))
     */
    public static String solution3(String[] strs) {
        if (check(strs)) {
            return "";
        }
        return longestCommonPrefix(strs, 0, strs.length - 1);
    }

    private static String longestCommonPrefix(String[] strs, int l, int r) {
        if (l == r) {
            return strs[l];
        } else {
            int mid = (l + r) / 2;
            String lcpLeft = longestCommonPrefix(strs, l, mid);
            String lcpRight = longestCommonPrefix(strs, mid + 1, r);
            return commonPrefix(lcpLeft, lcpRight);
        }
    }

    private static String commonPrefix(String lcpLeft, String lcpRight) {
        int min = Math.min(lcpLeft.length(), lcpRight.length());
        for (int i = 0; i < min; i++) {
            if (lcpLeft.charAt(i) != lcpRight.charAt(i)) {
                return lcpLeft.substring(0, i);
            }
        }
        return lcpLeft.substring(0, min);
    }

    /**
     * 方法四：
     * 二分查找法
     * 每一次将查找区间一分为二，然后丢弃一定不包含最终答案的那一个。
     * <p>
     * 时间复杂度：O(S⋅log(n))，其中 S 所有字符串中字符数量的总和。
     * 空间复杂度：O(1)，我们只需要使用常数级别的额外空间。
     */
    public static String solution4(String[] strs) {
        if (check(strs)) {
            return "";
        }
        int minLen = Integer.MAX_VALUE;
        for (String str : strs) {
            minLen = Math.min(minLen, str.length());
        }
        int left = 0;
        int right = minLen;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (isCommonPrefix(strs, mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return strs[0].substring(0, (left + right) / 2);
    }

    private static boolean isCommonPrefix(String[] strs, int len) {
        String str1 = strs[0].substring(0, len);
        for (int i = 1; i < strs.length; i++) {
            if (!strs[i].startsWith(str1)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution1([leetcode, leet, leetc, leetco, lee]) = " +
                solution1(new String[]{"leetcode", "leetode", "leetcod", "leetco", "lee"}));
        LeetCodeUtil.logln("solution2([leetcode, leet, leetc, leetco, lee]) = " +
                solution2(new String[]{"leetcode", "leetode", "leetcod", "leetco", "lee"}));
        LeetCodeUtil.logln("solution3([leetcode, leet, leetc, leetco, lee]) = " +
                solution3(new String[]{"leetcode", "leetode", "leetcod", "leetco", "lee"}));
        LeetCodeUtil.logln("solution4([leetcode, leet, leetc, leetco, lee]) = " +
                solution4(new String[]{"leetcode", "leetode", "leetcod", "leetco", "lee"}));
    }
}
