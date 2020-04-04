package com.wangcheng.leetcode.LeetCode.Array;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 *【题目】
 * 121.买卖股票的最佳时机
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * <p>
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 * 注意：你不能在买入股票前卖出股票。
 * <p>
 *【示例】
 * 示例 1:
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 *
 * 示例 2:
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 * @author liwangcheng
 * @date 2020/4/4.
 */
public class MaxProfit {

    /**
     * 方法一：暴力法
     * 解决方案
     * 需要找出给定数组中两个数字之间的最大差值（即，最大利润）。
     * 此外，第二个数字（卖出价格）必须大于第一个数字（买入价格）。
     *
     * 形式上，对于每组 i 和 j（其中 j > i）需要找出 max(prices[j]−prices[i])。
     *
     * 复杂度分析
     *
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     */
    public static int solution1(int[] nums) {
        int maxProfit = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int profit = nums[j] - nums[i];
                if (profit > maxProfit) {
                    maxProfit = profit;
                }
            }
        }
        return maxProfit;
    }

    /**
     * 方法二：一次遍历
     * 只需要遍历价格数组一遍，记录历史最低点，然后在每一天考虑这么一个问题：
     * 如果是在历史最低点买进的，那么今天卖出能赚多少钱？
     * 当考虑完所有天数之时，就得到了最好的答案。
     *
     * 复杂度分析
     * 时间复杂度：O(n)，只需要遍历一次。
     * 空间复杂度：O(1)，只使用了常数个变量。
     */
    public static int solution2(int[] nums) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < minPrice) {
                minPrice = nums[i];
            } else if (nums[i] - minPrice > maxProfit) {
                maxProfit = nums[i] - minPrice;
            }
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{7, 1, 5, 3, 6, 4};
        LeetCodeUtil.logln("solution1([7, 1, 5, 3, 6, 4]) = " + MaxProfit.solution1(nums));
        LeetCodeUtil.logln("solution2([7, 1, 5, 3, 6, 4]) = " + MaxProfit.solution2(nums));
    }
}
