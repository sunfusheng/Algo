package com.wangcheng.leetcode.LeetCode.Number;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 *【题目】
 * 258.各位相加
 * 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。
 *【示例】
 * 输入: 38
 * 输出: 2
 * 解释: 各位相加的过程为：3 + 8 = 11, 1 + 1 = 2。 由于 2 是一位数，所以返回 2。
 *【进阶】
 * 你可以不使用循环或者递归，且在 O(1) 时间复杂度内解决这个问题吗？
 *
 * @author liwangcheng
 * @date 2020/4/29.
 */
public class AddDigits {

    /**
     * 方法一：递归
     */
    public static int solution1(int num) {
        num = Math.abs(num);
        if (num < 10) {
            return num;
        }
        int next = 0;
        while (num != 0) {
            next += num % 10;
            num /= 10;
        }
        return solution1(next);
    }

    /**
     * 方法二：迭代
     */
    public static int solution2(int num) {
        while (num >= 10) {
            int next = 0;
            while (num != 0) {
                next += num % 10;
                num /= 10;
            }
            num = next;
        }
        return num;
    }

    /**
     * 方法三：数学
     * 在数学中，数根(又称位数根或数字根Digital root)是自然数的一种性质，
     * 换句话说，每个自然数都有一个数根。
     *
     * 数根是将一正整数的各个位数相加（即横向相加），若加完后的值大于10的话，
     * 则继续将各位数进行横向相加直到其值小于十为止[1]，或是，将一数字重复做
     * 数字和，直到其值小于十为止，则所得的值为该数的数根。
     *
     * 用途:
     * - 数根可以计算模运算的同余，对于非常大的数字的情况下可以节省很多时间。
     * - 数字根可作为一种检验计算正确性的方法。例如，两数字的和的数根等于两数字分别的数根的和。
     * - 另外，数根也可以用来判断数字的整除性，如果数根能被3或9整除，则原来的数也能被3或9整除。
     *
     * 参考：https://leetcode-cn.com/problems/add-digits/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-5-7/
     */
    public static int solution3(int num) {
        return (num - 1) % 9 + 1;
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution1(25) = " + AddDigits.solution1(25));
        LeetCodeUtil.logln("solution1(38) = " + AddDigits.solution1(38));
        LeetCodeUtil.logln("solution1(53917) = " + AddDigits.solution1(53917));
        LeetCodeUtil.logln("solution2(25) = " + AddDigits.solution2(25));
        LeetCodeUtil.logln("solution2(38) = " + AddDigits.solution2(38));
        LeetCodeUtil.logln("solution2(53917) = " + AddDigits.solution2(53917));
        LeetCodeUtil.logln("solution3(25) = " + AddDigits.solution3(25));
        LeetCodeUtil.logln("solution3(38) = " + AddDigits.solution3(38));
        LeetCodeUtil.logln("solution3(53917) = " + AddDigits.solution3(53917));
    }
}
