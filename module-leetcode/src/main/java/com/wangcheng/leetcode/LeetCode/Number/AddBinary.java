package com.wangcheng.leetcode.LeetCode.Number;

import java.math.BigInteger;

/**
 *【题目】
 * 67.二进制求和
 * 给定两个二进制字符串，返回他们的和（用二进制表示）。
 * <p>
 * 输入为非空字符串且只包含数字 1 和 0。
 * <p>
 *【示例】
 * 示例 1:
 * 输入: a = "11", b = "1"
 * 输出: "100"
 *
 * 示例 2:
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 *
 * @author liwangcheng
 * @date 2020/3/25.
 */
public class AddBinary {

    /**
     * 方法一：逐位计算
     * 初始进位 carry = 0，如果数字 a 的最低位是 1，则将 1 加到进位 carry；
     * 同理如果数字 b 的最低位是 1，则也将 1 加到进位。
     * 此时最低位有三种情况：
     * (00)_2，(01)_2 或者 (10)_2
     * 然后将 carry 的最低位作为最低位的值，将 carry 的最高位移至下一位继续计算。
     *
     * 复杂度分析
     * 时间复杂度：O(max(N,M))，其中 N 和 M 是输入字符串 a 和 b 的长度。
     * 空间复杂度：O(max(N,M))，存储求和结果。
     */
    public static String solution1(String a, String b) {
        if (null == a || a.isEmpty()) {
            return a;
        }
        if (null == b || b.isEmpty()) {
            return b;
        }
        int n = a.length();
        int m = b.length();
        if (n < m) {
            return solution1(b, a);
        }
        int carry = 0;
        StringBuilder builder = new StringBuilder();
        for (int i = n - 1, j = m - 1; i >= 0; i--) {
            if (a.charAt(i) == '1') {
                ++carry;
            }
            if (j > -1 && b.charAt(j--) == '1') {
                ++carry;
            }
            if (carry % 2 == 1) {
                builder.append('1');
            } else {
                builder.append('0');
            }
            carry /= 2;
        }
        if (carry == 1) {
            builder.append(1);
        }
        return builder.reverse().toString();
    }

    /**
     * 方法二：位操作
     * 思路
     * 如果不允许使用加法运算，则可以使用位操作。
     *
     * 现在问题被简化为：
     *  首先计算两个数字的无进位相加结果和进位，
     *  然后计算无进位相加结果与进位之和。
     *  同理求和问题又可以转换成上一步，
     *  直到进位为 0 结束。
     *
     *【算法】
     * 把 a 和 b 转换成整型数字 x 和 y，x 保存结果，y 保存进位。
     *  当进位不为 0：y != 0：
     *  计算当前 x 和 y 的无进位相加结果：answer = x^y。
     *  计算当前 x 和 y 的进位：carry = (x & y) << 1。
     *  完成本次循环，更新 x = answer，y = carry。
     *  返回 x 的二进制形式。
     *
     * 复杂度分析
     * 时间复杂度：O(N+M)，其中 N 和 M 是输入字符串 a 和 b 的长度。
     * 空间复杂度：O(max(N,M))，存储计算结果。
     */
    public static String solution2(String a, String b) {
        BigInteger x = new BigInteger(a, 2);
        BigInteger y = new BigInteger(b, 2);
        BigInteger zero = new BigInteger("0", 2);
        BigInteger carry;
        BigInteger answer;
        while (y.compareTo(zero) != 0) {
            answer = x.xor(y);
            carry = x.and(y).shiftLeft(1);
            x = answer;
            y = carry;
        }
        return x.toString(2);
    }

}
