package com.wangcheng.leetcode.LeetCode.Number;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 *【题目】
 * 191.位1的个数
 * 编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为 ‘1’ 的个数（也被称为汉明重量）。
 *【示例】
 * 示例 1：
 * 输入：00000000000000000000000000001011
 * 输出：3
 * 解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
 *
 * 示例 2：
 * 输入：00000000000000000000000010000000
 * 输出：1
 * 解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
 *
 * 进阶:
 * 如果多次调用这个函数，你将如何优化你的算法？
 *
 * @author liwangcheng
 * @date 2020/4/10.
 */
public class HammingWeight {

    /**
     * 方法 1：循环和位移动
     * 算法
     * 这个方法比较直接。遍历数字的 32 位。如果某一位是 11 ，将计数器加一。
     *
     * 复杂度分析
     * 时间复杂度：O(1)。运行时间依赖于数字 n 的位数。由于这题中 n 是一个 32 位数，所以运行时间是 O(1) 的。
     * 空间复杂度：O(1)。没有使用额外空间。
     */
    public static int solution1(int n) {
        int count = 0;
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            if ((n & mask) != 0) {
                count++;
            }
            mask <<= 1;
        }
        return count;
    }

    /**
     * 方法 2：位操作的小技巧
     * 不再检查数字的每一个位，而是不断把数字最后一个 1 反转，并把答案加一。
     * 当数字变成 0 的时候偶，就知道它没有 1 的位了，此时返回答案。
     *
     * 这里关键的想法是对于任意数字 n ，将 n 和 n−1 做与运算，
     * 会把最后一个 1 的位变成 0 。为什么？考虑 n 和 n−1 的二进制表示。
     * 在二进制表示中，数字 n 中最低位的 1 总是对应 n−1 中的 0。
     * 因此，将 n 和 n−1 与运算总是能把 n 中最低位的 1 变成 0，并保持其他位不变。
     *
     * 复杂度分析
     * 时间复杂度：O(1) 。运行时间与 n 中位为 1 的有关。
     * 在最坏情况下，n 中所有位都是 1。对于 32 位整数，运行时间是 O(1) 的。
     * 空间复杂度：O(1) 。没有使用额外空间。
     */
    public static int solution2(int n) {
        int sum = 0;
        while (n != 0) {
            n &= (n-1);
            sum++;
        }
        return sum;
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution1(1) = " + HammingWeight.solution1(1));
        LeetCodeUtil.logln("solution1(00000000000000000000000000001011) = " + HammingWeight.solution1(521));
        LeetCodeUtil.logln("solution2(1) = " + HammingWeight.solution2(1));
        LeetCodeUtil.logln("solution2(00000000000000000000000000001011) = " + HammingWeight.solution2(521));
    }
}
