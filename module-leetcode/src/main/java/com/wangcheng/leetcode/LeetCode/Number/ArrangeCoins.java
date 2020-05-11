package com.wangcheng.leetcode.LeetCode.Number;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 *【题目】
 * 441.排列硬币
 * 你总共有 n 枚硬币，你需要将它们摆成一个阶梯形状，
 * 第 k 行就必须正好有 k 枚硬币。
 * 给定一个数字 n，找出可形成完整阶梯行的总行数。
 * <p>
 * n 是一个非负整数，并且在32位有符号整型的范围内。
 * <p>
 *【示例】
 * 示例 1:
 * n = 5
 * 硬币可排列成以下几行:
 * ¤
 * ¤ ¤
 * ¤ ¤
 * 因为第三行不完整，所以返回2.
 *
 * 示例 2:
 * n = 8
 * 硬币可排列成以下几行:
 * ¤
 * ¤ ¤
 * ¤ ¤ ¤
 * ¤ ¤
 * 因为第四行不完整，所以返回3.
 *
 * @author liwangcheng
 * @date 2020/5/11.
 */
public class ArrangeCoins {

    /**
     * 方法一：暴力破解
     * 1+2···+i等于i*（i+1）/2
     */
    public static int solution1(int n) {
        if (n < 1) {
            return 0;
        }
        for (int i = 1; i < n; i++) {
            int temp = i * (i + 1) / 2;
            if (temp == n) {
                return i;
            }
            if (temp > n) {
                return i - 1;
            }
        }
        return 0;
    }

    /**
     * 方法二：数学公式
     * 根据数学公式，k(k+1)/2 = n，可以得到其正数解为：k = sqrt(2n+1/4) - 1/2。
     * 唯一的问题是，这里 2n+1/4 有可能会超出sqrt函数的参数范围。
     * 于是，可以变换一下， k = sqrt(2) * sqrt(n+1/8) - 1/2，这样求平方根就不会超限了。
     */
    public static int solution2(int n) {
        if (n < 1) {
            return 0;
        }
        return (int)(Math.sqrt(2) * Math.sqrt(n + 0.125) - 0.5);
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution1(5) = " + ArrangeCoins.solution1(5));
        LeetCodeUtil.logln("solution1(8) = " + ArrangeCoins.solution1(8));
        LeetCodeUtil.logln("solution2(5) = " + ArrangeCoins.solution2(5));
        LeetCodeUtil.logln("solution2(8) = " + ArrangeCoins.solution2(8));
    }
}
