package com.wangcheng.leetcode.LeetCode.String;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 *【题目】
 * 38.外观数列
 * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。前五项如下：
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 1 被读作  "one 1"  ("一个一") , 即 11。
 * 11 被读作 "two 1s" ("两个一"）, 即 21。
 * 21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211。
 * <p>
 * 给定一个正整数 n（1 ≤ n ≤ 30），输出外观数列的第 n 项。
 * <p>
 * 注意：整数序列中的每一项将表示为一个字符串。
 * <p>
 * 【示例】
 * 示例 1:
 * 输入: 1
 * 输出: "1"
 * 解释：这是一个基本样例。
 * <p>
 * 示例 2:
 * 输入: 4
 * 输出: "1211"
 * 解释：当 n = 3 时，序列是 "21"，其中我们有 "2" 和 "1" 两组，"2" 可以读作 "12"，也就是出现频次 = 1 而 值 = 2；类似 "1" 可以读作 "11"。所以答案是 "12" 和 "11" 组合在一起，也就是 "1211"。
 *
 * @author liwangcheng
 * @date 2020/3/23.
 */
public class CountAndSay {

    public static String solution1(int n) {
        String res = "1";
        for (int i = 2; i <= n; i++) {
            StringBuilder builder = new StringBuilder();
            char pre = res.charAt(0);
            // 统计有几个相同的
            int count = 1;
            for (int j = 1; j < res.length(); j++) {
                char c = res.charAt(j);
                // 后一个字符与前一个同
                if (c == pre) {
                    count++;
                } else {
                    // 拼接count个pre
                    builder.append(count).append(pre);
                    // 前缀等于不同处
                    pre = c;
                    count = 1;
                }
            }
            // 拼接count个pre
            builder.append(count).append(pre);
            res = builder.toString();
        }
        return res;
    }

    public static String solution2(int n) {
        if (n == 1) {
            return "1";
        }
        String strLast = solution2(n - 1);
        StringBuilder builder = new StringBuilder();
        int count = 1;
        for (int i = 0, size = strLast.length(); i < size; i++) {
            if (i + 1 < size && strLast.charAt(i) == strLast.charAt(i + 1)) {
                count++;
            } else {
                builder.append(count);
                builder.append(strLast.charAt(i));
                count = 1;
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution1(1) = " + CountAndSay.solution1(1));
        LeetCodeUtil.logln("solution1(2) = " + CountAndSay.solution1(2));
        LeetCodeUtil.logln("solution1(3) = " + CountAndSay.solution1(3));
        LeetCodeUtil.logln("solution1(4) = " + CountAndSay.solution1(4));
        LeetCodeUtil.logln("solution1(5) = " + CountAndSay.solution1(5));
        LeetCodeUtil.logln("solution2(1) = " + CountAndSay.solution2(1));
        LeetCodeUtil.logln("solution2(2) = " + CountAndSay.solution2(2));
        LeetCodeUtil.logln("solution2(3) = " + CountAndSay.solution2(3));
        LeetCodeUtil.logln("solution2(4) = " + CountAndSay.solution2(4));
        LeetCodeUtil.logln("solution2(5) = " + CountAndSay.solution2(5));
    }
}
