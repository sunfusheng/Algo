package com.wangcheng.leetcode.LeetCode.Number;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 *【题目】
 * 172.阶乘后的零
 * 给定一个整数 n，返回 n! 结果尾数中零的数量。
 *【示例】
 * 示例 1:
 * 输入: 3
 * 输出: 0
 * 解释: 3! = 6, 尾数中没有零。
 *
 * 示例 2:
 * 输入: 5
 * 输出: 1
 * 解释: 5! = 120, 尾数中有 1 个零.
 *
 *【说明】
 * 你算法的时间复杂度应为 O(log n) 。
 *
 * @author liwangcheng
 * @date 2020/4/9.
 */
public class TrailingZeroes {

    /**
     * 思路
     * 1.首先末尾有多少个 0 ，只需要给当前数乘以一个 10 就可以加一个 0（10 = 2 * 5）。
     * 2.含有 2 的因子每两个出现一次，含有 5 的因子每 5 个出现一次，所有 2 出现的个数远远多于 5，
     * 换言之找到一个 5，一定能找到一个 2 与之配对。所以只需要找有多少个 5。
     * ==> 直接的，只需要判断每个累乘的数有多少个 5 的因子即可
     * 3.因为每隔 5 个数出现一个 5，所以计算出现了多少个 5，只需要用 n/5 就可以算出来。
     * ==> 规律就是每隔 5 个数，出现一个 5，每隔 25 个数，出现 2 个 5，
     * 每隔 125 个数，出现 3 个 5... 以此类推
     */
    public static int solution(int num) {
        int count = 0;
        while (num > 0) {
            num /= 5;
            count += num;
        }
        return count;
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution(5) = " + TrailingZeroes.solution(5));
        LeetCodeUtil.logln("solution(10) = " + TrailingZeroes.solution(10));
        LeetCodeUtil.logln("solution(17) = " + TrailingZeroes.solution(17));
    }
}
