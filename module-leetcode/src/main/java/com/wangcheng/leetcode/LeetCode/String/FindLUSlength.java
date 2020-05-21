package com.wangcheng.leetcode.LeetCode.String;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *【题目】
 * 521.最长特殊序列 Ⅰ
 * 给你两个字符串，请你从这两个字符串中找出最长的特殊序列。
 * <p>
 * 「最长特殊序列」定义如下：该序列为某字符串独有的最长子序列
 * （即不能是其他字符串的子序列）。
 * <p>
 * 子序列 可以通过删去字符串中的某些字符实现，但不能改变剩余
 * 字符的相对顺序。空序列为所有字符串的子序列，任何字符串为
 * 其自身的子序列。
 * <p>
 * 输入为两个字符串，输出最长特殊序列的长度。如果不存在，则返回 -1。
 * <p>
 * 【示例】
 * 示例 1：
 * 输入: "aba", "cdc"
 * 输出: 3
 * 解释: 最长特殊序列可为 "aba" (或 "cdc")，两者均为自身的子序列且不是对方的子序列。
 * <p>
 * 示例 2：
 * 输入：a = "aaa", b = "bbb"
 * 输出：3
 * <p>
 * 示例 3：
 * 输入：a = "aaa", b = "aaa"
 * 输出：-1
 * <p>
 * 提示：
 * 两个字符串长度均处于区间 [1 - 100]。
 * 字符串中的字符仅含有 'a'~'z'。
 *
 * @author liwangcheng
 * @date 2020/5/21.
 */
public class FindLUSlength {

    /**
     * 方法一：暴力解法 【超出时间限制】
     * 暴力解法中，生成两个字符串所有的子序列共 2^n 个，将其存储在
     * hashmap 中，并记录每个子序列出现的次数。然后找出出现次数为
     * 1 的最长子序列。如果不存在这样的子序列，返回 −1。
     * <p>
     * 复杂度分析
     * 时间复杂度：O(2^x+2^y)，其中 x 和 y 是字符串 a 和 b 的长度，
     * 子序列的数量为 2^x+2^y。
     * 空间复杂度：O(2^x+2^y)，共生成 2^x+2^y个子序列。
     */
    public static int solution1(String a, String b) {
        Map<String, Integer> map = new HashMap<>();
        for (String s : new String[]{a, b}) {
            for (int i = 0; i < (1 << s.length()); i++) {
                String t = "";
                for (int j = 0; j < s.length(); j++) {
                    if (((i >> j) & 1) != 0) {
                        t += s.charAt(j);
                    }
                }
                if (map.containsKey(t)) {
                    map.put(t, map.get(t) + 1);
                } else {
                    map.put(t, 1);
                }
            }
        }
        int res = -1;
        for (String s : map.keySet()) {
            if (map.get(s) == 1) {
                res = Math.max(res, s.length());
            }
        }
        return res;
    }

    /**
     * 方法二：简单解法 【通过】
     * 算法
     * 字符串 a 和 b 共有 3 种情况：
     * - a=b。如果两个字符串相同，则没有特殊子序列，返回 -1。
     * - length(a) == length(b) 且 a != b。例如：abc 和 abd。
     *   这种情况下，一个字符串一定不会是另外一个字符串的子序列，
     *   因此可以将任意一个字符串看作是特殊子序列，返回 length(a)
     *   或 length(b)。
     * - length(a) != length(b)。例如：abcd 和 abc。这种情况下，
     *   长的字符串一定不会是短字符串的子序列，因此可以将长字符串看作
     *   是特殊子序列，返回 max(length(a),length(b))。
     *
     * 复杂度分析
     * 时间复杂度：O(min(x,y))，其中 x 和 y 是字符串 a 和 b 的长度。
     *  方法 equals 的时间复杂度为 min(x,y)。
     * 空间复杂度：O(1)，无需额外空间。
     */
    public static int solution2(String a, String b) {
        if (Objects.equals(a, b)) {
            return -1;
        }
        return Math.max(a.length(), b.length());
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution1(aba, cdc) = " + FindLUSlength.solution1("aba", "cdc"));
        LeetCodeUtil.logln("solution1(aaa, bbb) = " + FindLUSlength.solution1("aaa", "bbb"));
        LeetCodeUtil.logln("solution1(aaa, aaa) = " + FindLUSlength.solution1("aaa", "aaa"));
        LeetCodeUtil.logln("solution2(aba, cdc) = " + FindLUSlength.solution2("aba", "cdc"));
        LeetCodeUtil.logln("solution2(aaa, bbb) = " + FindLUSlength.solution2("aaa", "bbb"));
        LeetCodeUtil.logln("solution2(aaa, aaa) = " + FindLUSlength.solution2("aaa", "aaa"));
    }
}
