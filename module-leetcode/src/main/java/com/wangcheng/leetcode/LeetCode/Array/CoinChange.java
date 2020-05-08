package com.wangcheng.leetcode.LeetCode.Array;

/**
 * 【题目】
 * 322.零钱兑换
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
 * 如果没有任何一种硬币组合能组成总金额，返回 -1。
 * <p>
 * 示例 1:
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 * <p>
 * 示例 2:
 * 输入: coins = [2], amount = 3
 * 输出: -1
 * <p>
 * 说明：你可以认为每种硬币的数量是无限的。
 *
 * @author sunfusheng
 * @since 2020/5/8
 */
public class CoinChange {

    /**
     * 方法一：记忆化搜索
     * <p>
     * 时间复杂度：O(S^n)，其中 S 是金额，n 是面额数
     * 空间复杂度：O(S)
     *
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        return coinChange(coins, amount, new int[amount + 1]);
    }

    private static int coinChange(int[] coins, int amount, int[] mem) {
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        if (mem[amount] != 0) {
            return mem[amount];
        }

        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChange(coins, amount - coin, mem);
            if (res >= 0 && res < min) {
                min = res + 1;
            }
        }
        mem[amount] = min == Integer.MAX_VALUE ? -1 : min;
        return mem[amount];
    }

    public static void main(String[] args) {
        int[] coins = new int[]{1, 2, 5};
        int[] coins2 = new int[]{2};
        System.out.println("方法一输出1：" + coinChange(coins, 11));
        System.out.println("方法一输出2：" + coinChange(coins2, 3));
    }
}
