package com.wangcheng.leetcode.LeetCode.Number;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 *【题目】
 * 342.4的幂
 * 给定一个整数 (32 位有符号整数)，请编写一个函数来判断它是否是 4 的幂次方。
 *【示例】
 * 示例 1:
 * 输入: 16
 * 输出: true
 *
 * 示例 2:
 * 输入: 5
 * 输出: false
 *【进阶】
 * 你能不使用循环或者递归来完成本题吗？
 *
 * @author liwangcheng
 * @date 2020/5/4.
 */
public class IsPowerOfFour {

    /**
     * 方法一：暴力法
     */
    public static boolean solution1(int num) {
        if (num < 1) {
            return false;
        }
        while (num % 4 == 0) {
            num /= 4;
        }
        return num == 1;
    }

    /**
     * 方法二：暴力法 + 预计算
     * 提前计算所有可能答案。
     * 预计算全部可能，然后运行时检查输入数字是否在预计算列表中。
     *
     * 复杂度分析
     * 时间复杂度：O(1)。
     * 空间复杂度：O(1)。
     */
    private static int N = 15;
    private static List<Integer> N_LIST = new ArrayList<>();
    static {
        int lastNum = 1;
        N_LIST.add(lastNum);
        for (int i = 1; i <= N; i++) {
            lastNum *= 4;
            N_LIST.add(lastNum);
        }
    }
    public static boolean solution2(int num) {
        return N_LIST.contains(num);
    }

    /**
     * 方法三：数学运算
     * 算法：
     * 如果数字为 4 的幂 x = 4^a，则 a = log_4 x = 1/2log_2 x应为整数，
     * 那么检查 log_2 x 是否为偶数就能判断 x 是否为 4 的幂。
     *
     * 复杂度分析
     * 时间复杂度：O(1)。
     * 空间复杂度：O(1)。
     */
    public static boolean solution3(int num) {
        return (num > 0) && (Math.log(num) / Math.log(2) % 2 == 0);
    }

    /**
     * 方法四：位运算
     *
     * 复杂度分析
     * 时间复杂度：O(1)。
     * 空间复杂度：O(1)。
     */
    public static boolean solution4(int num) {
        return (num > 0) && ((num & (num - 1)) == 0) && ((num & 0xaaaaaaaa) == 0);
    }

    /**
     * 方法五：位运算 + 数学运算
     *
     * 复杂度分析
     * 时间复杂度：O(1)。
     * 空间复杂度：O(1)。
     */
    public static boolean solution5(int num) {
        return (num > 0) && ((num & (num - 1)) == 0) && (num % 3 == 1);
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution1(16) = " + IsPowerOfFour.solution1(16));
        LeetCodeUtil.logln("solution1(8) = " + IsPowerOfFour.solution1(8));
        LeetCodeUtil.logln("solution1(64) = " + IsPowerOfFour.solution1(64));
        LeetCodeUtil.logln("solution2(16) = " + IsPowerOfFour.solution2(16));
        LeetCodeUtil.logln("solution2(8) = " + IsPowerOfFour.solution2(8));
        LeetCodeUtil.logln("solution2(64) = " + IsPowerOfFour.solution2(64));
        LeetCodeUtil.logln("solution3(16) = " + IsPowerOfFour.solution3(16));
        LeetCodeUtil.logln("solution3(8) = " + IsPowerOfFour.solution3(8));
        LeetCodeUtil.logln("solution3(64) = " + IsPowerOfFour.solution3(64));
        LeetCodeUtil.logln("solution4(16) = " + IsPowerOfFour.solution4(16));
        LeetCodeUtil.logln("solution4(8) = " + IsPowerOfFour.solution4(8));
        LeetCodeUtil.logln("solution4(64) = " + IsPowerOfFour.solution4(64));
        LeetCodeUtil.logln("solution5(16) = " + IsPowerOfFour.solution5(16));
        LeetCodeUtil.logln("solution5(8) = " + IsPowerOfFour.solution5(8));
        LeetCodeUtil.logln("solution5(64) = " + IsPowerOfFour.solution5(64));
    }
}
