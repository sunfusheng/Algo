package com.wangcheng.leetcode.LeetCode.Number;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 *【题目】
 * 69.x的平方根
 * 实现 int sqrt(int x) 函数。
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * <p>
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * <p>
 *【示例】
 * 输入: 4
 * 输出: 2
 * 示例 2:
 *
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 *      由于返回类型是整数，小数部分将被舍去。
 *
 * @author liwangcheng
 * @date 2020/3/26.
 */
public class MySqrt {

    /**
     * 方法一：袖珍计算器算法
     * 通常，袖珍计算器通过对数表或其他方式计算指数函数和自然对数。
     * 那么考虑将求平方根的运算转换为指数运算和对数运算：
     * sqrt(x) = e^(1/2log x)
     * 此方法中使用到了对数表与其他策略，省略了一部分计算。
     * 但实际上对数函数和指数函数本身就是这样计算的。
     */
    public static int solution1(int x) {
        if (x < 2) {
            return x;
        }
        int left = (int) Math.pow(Math.E, 0.5 * Math.log(x));
        int right = left + 1;
        return right * right > x ? left : right;
    }

    /**
     * 方法二：二叉查找
     * 思路
     * 当 x≥2 时，它的整数平方根一定小于 x/2 且大于 0，
     * 即 0<a<x/2。由于 a 一定是整数，此问题可以转换成
     * 在有序整数集中寻找一个特定值，因此可以使用二分查找。
     *
     * 复杂度分析
     * 时间复杂度：O(logN)。
     * 空间复杂度：O(1)。
     */
    public static int solution2(int x) {
        if (x < 2) {
            return x;
        }
        int left = 2;
        int right = x / 2;
        int pivot;
        while (left <= right) {
            pivot = (left + right) / 2;
            int num = pivot * pivot;
            if (num == x) {
                return pivot;
            } else if (num < x) {
                left = pivot + 1;
            } else {
                right = pivot - 1;
            }
        }
        return right;
    }

    /**
     * 方法三：递归+位操作
     * 思路
     * 本方法的思路是使用递归，每一步都减小 x，直到 x<2。
     * 当 x>=2 时，减小方法如下：
     * mySqrt(x)=2×mySqrt(x/4)
     * 减小 x 可以用左移或右移实现，因为这种方式计算效率非常高。
     * x<<y 表示 x*(2^y)
     * x>>y 表示 x/(2^y)
     *
     * 复杂度分析
     * 时间复杂度：O(logN)。
     * 空间复杂度：O(logN)，递归栈的深度。
     */
    public static int solution3(int x) {
        if (x < 2) {
            return x;
        }
        int left = solution3(x >> 2)<<1;
        int right = left + 1;
        return right * right > x ? left : right;
    }

    /**
     * 方法四：牛顿法
     * 思路
     * 计算平方根，最好和使用最多的方法是牛顿法，
     * 这里使用不带种子修剪版本的牛顿法简化此问题。
     * X    = 1/2 * (X  + X/X )
     *  k+1           k      k
     *
     * TODO 我等凡人只能记住结论
     *
     * 复杂度分析
     * 时间复杂度：O(logN)，此方法是二次收敛的。
     * 空间复杂度：O(1)。
     */
    public static int solution4(int x) {
        if (x < 2) {
            return x;
        }
        double x0 = x;
        double x1 = (x0 + x/x0)/2.0;
        while (Math.abs(x0 - x1) >= 1) {
            x0 = x1;
            x1 = (x0 + x/x0)/2.0;
        }
        return (int) x1;
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution1(4) = " + solution1(4));
        LeetCodeUtil.logln("solution1(15) = " + solution1(15));
        LeetCodeUtil.logln("solution1(36) = " + solution1(36));
        LeetCodeUtil.logln("solution1(47) = " + solution1(47));
        LeetCodeUtil.logln("solution1(58) = " + solution1(58));
        LeetCodeUtil.logln("solution1(2147395599) = " + solution1(2147395599));
        LeetCodeUtil.logln("solution2(4) = " + solution2(4));
        LeetCodeUtil.logln("solution2(15) = " + solution2(15));
        LeetCodeUtil.logln("solution2(36) = " + solution2(36));
        LeetCodeUtil.logln("solution2(47) = " + solution2(47));
        LeetCodeUtil.logln("solution2(58) = " + solution2(58));
        LeetCodeUtil.logln("solution2(2147395599) = " + solution2(2147395599));
        LeetCodeUtil.logln("solution3(4) = " + solution3(4));
        LeetCodeUtil.logln("solution3(15) = " + solution3(15));
        LeetCodeUtil.logln("solution3(36) = " + solution3(36));
        LeetCodeUtil.logln("solution3(47) = " + solution3(47));
        LeetCodeUtil.logln("solution3(58) = " + solution3(58));
        LeetCodeUtil.logln("solution3(2147395599) = " + solution3(2147395599));
        LeetCodeUtil.logln("solution4(4) = " + solution4(4));
        LeetCodeUtil.logln("solution4(15) = " + solution4(15));
        LeetCodeUtil.logln("solution4(36) = " + solution4(36));
        LeetCodeUtil.logln("solution4(47) = " + solution4(47));
        LeetCodeUtil.logln("solution4(58) = " + solution4(58));
        LeetCodeUtil.logln("solution4(2147395599) = " + solution4(2147395599));
    }

}
