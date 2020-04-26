package com.wangcheng.leetcode.LeetCode.Number;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 *【题目】
 * 231.2的幂
 * 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
 *【示例】
 * 示例 1:
 * 输入: 1
 * 输出: true
 * 解释: 20 = 1
 *
 * 示例 2:
 * 输入: 218
 * 输出: false
 *
 * @author liwangcheng
 * @date 2020/4/26.
 */
public class IsPowerOfTwo {

    /**
     * 方法一：暴力破解
     * 时间复杂度为 O(logN)
     */
    public static boolean solution1(int num) {
        if (num <= 0) {
            return false;
        }
        while (num % 2 == 0) {
            num /= 2;
        }
        return num == 1;
    }

    /**
     * 该问题将通过位运算在 O(1) 的时间复杂度解决，通过使用如下的按位技巧：
     * - 如何获取二进制中最右边的 1：x & (-x)。
     * - 如何将二进制中最右边的 1 设置为 0：x & (x - 1)。
     * 以下的两种解决方案背后的思想都是一样的：
     * 2 的幂在二进制中是有一个 1 后跟一些 0：
     * 1 = (0000 0001)_2
     * 2 = (0000 0010)_2
     * 4 = (0000 0100)_2
     * 8 = (0000 1000)_2
     * 不是 2 的幂的二进制中有一个以上的 1。
     */

    /**
     * 方法二：位运算：获取二进制中最右边的 1
     * 算法：
     * 获取最右边的 1：
     * x & (-x) 可以获取到二进制中最右边的 1，且其它位设置为 0。
     * 检测是否为 2 的幂：
     * 通过 x & (-x) 保留了最右边的 1，并将其他位设置为 0 若 x 为 2 的幂，
     * 则它的二进制表示中只包含一个 1，则有 x & (-x) = x。
     * 若 x 不是 2 的幂，则在二进制表示中存在其他 1，因此 x & (-x) != x。
     * 因此判断是否为 2 的幂的关键是：判断 x & (-x) == x。
     *
     * 复杂度分析
     * 时间复杂度：O(1)。
     * 空间复杂度：O(1)。
     * 参考：https://leetcode-cn.com/problems/power-of-two/solution/2de-mi-by-leetcode/
     */
    public static boolean solution2(int num) {
        if (num <= 0) {
            return false;
        }
        long x = (long) num;
        return (x & (-x)) == x;
    }

    /**
     * 方法三：位运算：去除二进制中最右边的 1
     * 算法：
     * 去除二进制中最右边的 1：
     * 首先讨论为什么 x & (x - 1) 可以将最右边的 1 设置为 0。
     * (x - 1) 代表了将 x 最右边的 1 设置为 0，并且将较低位设置为 1。
     * 再使用与运算：则 x 最右边的 1 和就会被设置为 0，因为 1 & 0 = 0。
     * 检测是否为 2 的幂：
     * - 2 的幂二进制表示只含有一个 1。
     * - x & (x - 1) 操作会将 2 的幂设置为 0，
     * 因此判断是否为 2 的幂是：判断 x & (x - 1) == 0。
     *
     * 复杂度分析
     * 时间复杂度：O(1)。
     * 空间复杂度：O(1)。
     */
    public static boolean solution3(int num) {
        if (num <= 0) {
            return false;
        }
        return (num & (num - 1)) == 0;
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution1(4) = " + IsPowerOfTwo.solution1(4));
        LeetCodeUtil.logln("solution1(5) = " + IsPowerOfTwo.solution1(5));
        LeetCodeUtil.logln("solution1(8) = " + IsPowerOfTwo.solution1(8));
        LeetCodeUtil.logln("solution1(10) = " + IsPowerOfTwo.solution1(10));
        LeetCodeUtil.logln("solution1(218) = " + IsPowerOfTwo.solution1(218));
        LeetCodeUtil.logln("solution2(4) = " + IsPowerOfTwo.solution2(4));
        LeetCodeUtil.logln("solution2(5) = " + IsPowerOfTwo.solution2(5));
        LeetCodeUtil.logln("solution2(8) = " + IsPowerOfTwo.solution2(8));
        LeetCodeUtil.logln("solution2(10) = " + IsPowerOfTwo.solution2(10));
        LeetCodeUtil.logln("solution2(218) = " + IsPowerOfTwo.solution2(218));
        LeetCodeUtil.logln("solution3(4) = " + IsPowerOfTwo.solution3(4));
        LeetCodeUtil.logln("solution3(5) = " + IsPowerOfTwo.solution3(5));
        LeetCodeUtil.logln("solution3(8) = " + IsPowerOfTwo.solution3(8));
        LeetCodeUtil.logln("solution3(10) = " + IsPowerOfTwo.solution3(10));
        LeetCodeUtil.logln("solution3(218) = " + IsPowerOfTwo.solution3(218));
    }
}
