package com.wangcheng.leetcode.LeetCode.Number;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 * 【题目】
 * 9. 回文数
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * 【示例】
 * 示例 1:
 * 输入: 121
 * 输出: true
 *
 * 示例 2:
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 *
 * 【进阶】
 * 你能不将整数转为字符串来解决这个问题吗？
 *
 * @author liwangcheng
 * @date 2020/3/17.
 */
public class PalindromeNum {

    private static final int TEN = 10;

    public static boolean solution(int x) {
        if (x < 0 || (x % TEN == 0 && x != 0)) {
            return false;
        }
        int rev = 0;
        while (x > rev) {
            rev = rev * 10 + x % 10;
            x /= 10;
        }
        return rev == x || x == rev / 10;
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution(121) = " + PalindromeNum.solution(121));
        LeetCodeUtil.logln("solution(122) = " + PalindromeNum.solution(122));
        LeetCodeUtil.logln("solution(1221) = " + PalindromeNum.solution(1221));
    }
}
