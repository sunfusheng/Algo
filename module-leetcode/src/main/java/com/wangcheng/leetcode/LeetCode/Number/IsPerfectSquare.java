package com.wangcheng.leetcode.LeetCode.Number;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 *【题目】
 * 367.有效的完全平方数
 * 给定一个正整数 num，编写一个函数，如果 num 是一个完全平方数，则返回 True，否则返回 False。
 * <p>
 * 说明：不要使用任何内置的库函数，如  sqrt。
 * <p>
 *【示例】
 * 示例 1：
 * 输入：16
 * 输出：True
 *
 * 示例 2：
 * 输入：14
 * 输出：False
 *
 * @author liwangcheng
 * @date 2020/5/5.
 */
public class IsPerfectSquare {

    /**
     * 方法一：二分查找
     * 算法：
     * - 若 num < 2，返回 true。
     * - 设置左边界为 2，右边界为 num/2。
     * - 当 left <= right：
     *   - 令 x = (left + right) / 2 作为一个猜测，
     *   计算 guess_squared = x * x 与 num 做比较：
     *     - 如果 guess_squared == num，则 num 是一个完全平方数，返回 true。
     *     - 如果 guess_squared > num ，设置右边界 right = x-1。
     *     - 否则设置左边界为 left = x+1。
     * - 如果在循环体内没有找到，则说明 num 不是完全平方数，返回 false。
     *
     * 复杂度分析
     * 时间复杂度：O(logN)。
     * 空间复杂度：O(1)。
     */
    public static boolean solution1(int num) {
        if (num < 0) {
            return false;
        }
        if (num < 2) {
            return true;
        }
        int left = 2;
        int right = num>>1;
        while (left <= right) {
            int mid = left + (right-left)/2;
            int temp = mid * mid;
            if (temp == num) {
                return true;
            } else if (temp > num) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }

    /**
     * 方法二：牛顿迭代法
     * 牛顿迭代法：公式是如何推导的呢？让我们做一个非常粗略的推导。
     * 问题是找出：f(x) = x^2 − num = 0 的根。
     * 牛顿迭代法的思想是从一个初始近似值开始，然后作一系列改进的逼近根的过程。
     *
     * 复杂度分析
     * 时间复杂度：O(logN)。
     * 空间复杂度：O(1)。
     */
    public static boolean solution2(int num) {
        if (num < 0) {
            return false;
        }
        if (num < 2) {
            return true;
        }
        long x = num / 2;
        while (x * x > num) {
            x = (x + num / x) / 2;
        }
        return (x * x == num);
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution1(16) = " + IsPerfectSquare.solution1(16));
        LeetCodeUtil.logln("solution1(18) = " + IsPerfectSquare.solution1(18));
        LeetCodeUtil.logln("solution1(255) = " + IsPerfectSquare.solution1(255));
        LeetCodeUtil.logln("solution1(144) = " + IsPerfectSquare.solution1(144));
        LeetCodeUtil.logln("solution1(9801) = " + IsPerfectSquare.solution1(9801));
        LeetCodeUtil.logln("solution1(9901) = " + IsPerfectSquare.solution1(9901));
        LeetCodeUtil.logln("solution2(16) = " + IsPerfectSquare.solution2(16));
        LeetCodeUtil.logln("solution2(18) = " + IsPerfectSquare.solution2(18));
        LeetCodeUtil.logln("solution2(255) = " + IsPerfectSquare.solution2(255));
        LeetCodeUtil.logln("solution2(144) = " + IsPerfectSquare.solution2(144));
        LeetCodeUtil.logln("solution2(9801) = " + IsPerfectSquare.solution2(9801));
        LeetCodeUtil.logln("solution2(9901) = " + IsPerfectSquare.solution2(9901));
    }
}
