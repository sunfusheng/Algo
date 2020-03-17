package com.wangcheng.leetcode.LeetCode.Number;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 *【题目】
 * 7. 整数反转
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * 【示例】
 * 示例 1:
 * 输入: 123
 * 输出: 321
 *
 * 示例 2:
 * 输入: -123
 * 输出: -321
 *
 * 示例 3:
 * 输入: 120
 * 输出: 21
 *
 * 【注意】
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 
 * [−2^31,  2^31 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 *
 * @author liwangcheng
 * @date 2020/3/17.
 */
public class ReverseInt {

    private static final int MAX = Integer.MAX_VALUE / 10;
    private static final int MIN = Integer.MIN_VALUE / 10;
    private static final int MAX_LAST = Integer.MAX_VALUE % 10;
    private static final int MIN_LAST = Integer.MIN_VALUE % 10;

    public int solution(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > MAX || (rev == MAX && pop > MAX_LAST)) {
                return 0;
            }
            if (rev < MIN || (rev == MIN && pop < MIN_LAST)) {
                return 0;
            }
            rev = rev * 10 + pop;
        }
        return rev;
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution(123) = " + new ReverseInt().solution(123));
    }
}
