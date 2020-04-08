package com.wangcheng.leetcode.LeetCode.Number;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 *【题目】
 * 168.Excel表列名称
 * 给定一个正整数，返回它在 Excel 表中相对应的列名称。
 * <p>
 * 例如，
 *     1 -> A
 *     2 -> B
 *     3 -> C
 *     ...
 *     26 -> Z
 *     27 -> AA
 *     28 -> AB
 *     ...
 * <p>
 *【示例】
 * 示例 1:
 * 输入: 1
 * 输出: "A"
 *
 * 示例 2:
 * 输入: 28
 * 输出: "AB"
 *
 * 示例 3:
 * 输入: 701
 * 输出: "ZY"
 *
 * @author liwangcheng
 * @date 2020/4/8.
 */
public class ConvertToTitle {

    public static String solution1(int n) {
        StringBuilder builder = new StringBuilder();
        while (n > 0) {
            int mod = (n - 1) % 26;
            builder.insert(0, (char) ('A' + mod));
            n = (n - 1) / 26;
        }
        return builder.toString();
    }

    public static String solution2(int n) {
        String aZ = "#ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] cZ = aZ.toCharArray();
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            if (n % 26 == 0) {
                sb.insert(0, 'Z');
                n = n / 26 - 1;
            } else {
                sb.insert(0, cZ[n % 26]);
                n = n / 26;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution1(1) = " + ConvertToTitle.solution1(1));
        LeetCodeUtil.logln("solution1(28) = " + ConvertToTitle.solution1(28));
        LeetCodeUtil.logln("solution1(701) = " + ConvertToTitle.solution1(701));
        LeetCodeUtil.logln("solution2(1) = " + ConvertToTitle.solution2(1));
        LeetCodeUtil.logln("solution2(28) = " + ConvertToTitle.solution2(28));
        LeetCodeUtil.logln("solution2(701) = " + ConvertToTitle.solution2(701));
    }
}
