package com.wangcheng.leetcode.LeetCode.Array;

import com.sunfusheng.algo.common.util.LeetCodeUtil;
import com.wangcheng.leetcode.util.UtilsKt;

/**
 * 【题目】
 * 122.买卖股票的最佳时机 II
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * <p>
 * 设计一个算法来计算你所能获取的最大利润。
 * 你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * 【示例】
 * 示例 1:
 * 输入: [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出,
 * 这笔交易所能获得利润 = 5-1 = 4 。随后，在第 4 天（股票价格 = 3）的时候买入，
 * 在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 * <p>
 * 示例 2:
 * 输入: [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出,
 * 这笔交易所能获得利润 = 5-1 = 4 。
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * <p>
 * 示例 3:
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 * @author liwangcheng
 * @date 2020/4/4.
 */
public class MaxProfitII {

    /**
     * 方法一：暴力法
     * 这种情况下，只需要计算与所有可能的交易组合相对应的利润，
     * 并找出它们中的最大利润。
     * <p>
     * 复杂度分析
     * 时间复杂度：O(n^n)，调用递归函数 n^n次。
     * 空间复杂度：O(n)，递归的深度为 n。
     */
    public static int solution1(int[] prices) {
        if (UtilsKt.isEmpty(prices)) {
            return 0;
        }
        return calculate(prices, 0);
    }

    private static int calculate(int[] prices, int s) {
        if (s >= prices.length) {
            return 0;
        }
        int max = 0;
        for (int start = s; start < prices.length; start++) {
            int maxprofit = 0;
            for (int i = start + 1; i < prices.length; i++) {
                if (prices[start] < prices[i]) {
                    int profit = calculate(prices, i + 1) + prices[i] - prices[start];
                    if (profit > maxprofit) {
                        maxprofit = profit;
                    }
                }
            }
            if (maxprofit > max) {
                max = maxprofit;
            }
        }
        return max;
    }

    /**
     * 方法二：峰谷法
     * 关键是需要考虑到紧跟谷的每一个峰值以最大化利润。如果试图跳过其中一个峰值
     * 来获取更多利润，那么最终将失去其中一笔交易中获得的利润，从而导致总利润的降低。
     * <p>
     * 复杂度分析
     * 时间复杂度：O(n)。遍历一次。
     * 空间复杂度：O(1)。需要常量的空间。
     */
    public static int solution2(int[] prices) {
        if (UtilsKt.isEmpty(prices)) {
            return 0;
        }
        int i = 0;
        // 记录谷
        int valley = prices[0];
        // 记录峰
        int peak = prices[0];
        int maxProfit = 0;
        while (i < prices.length - 1) {
            while (i < prices.length - 1 && prices[i] >= prices[i + 1]) {
                i++;
            }
            valley = prices[i];
            while (i < prices.length - 1 && prices[i] <= prices[i + 1]) {
                i++;
            }
            peak = prices[i];
            maxProfit += peak - valley;
        }
        return maxProfit;
    }

    /**
     * 方法三：简单的一次遍历
     * 该解决方案遵循 方法二 的本身使用的逻辑，但有一些轻微的变化。
     * 在这种情况下，可以简单地继续在斜坡上爬升并持续增加从连续交易中获得的利润，
     * 而不是在谷之后寻找每个峰值。最后，将有效地使用峰值和谷值，但不需要跟踪峰值
     * 和谷值对应的成本以及最大利润，但可以直接继续增加加数组的连续数字之间的差值，
     * 如果第二个数字大于第一个数字，获得的总和将是最大利润。这种方法将简化解决方案。
     * <p>
     * 复杂度分析
     * 时间复杂度：O(n)，遍历一次。
     * 空间复杂度：O(1)，需要常量的空间。
     */
    public static int solution3(int[] prices) {
        if (UtilsKt.isEmpty(prices)) {
            return 0;
        }
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                maxProfit += prices[i] - prices[i - 1];
            }
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices = new int[]{1, 7, 2, 3, 6, 7, 6, 7};
        LeetCodeUtil.logln("solution1([1, 7, 2, 3, 6, 7, 6, 7]) = " + solution1(prices));
        LeetCodeUtil.logln("solution2([1, 7, 2, 3, 6, 7, 6, 7]) = " + solution2(prices));
        LeetCodeUtil.logln("solution3([1, 7, 2, 3, 6, 7, 6, 7]) = " + solution3(prices));
    }
}
