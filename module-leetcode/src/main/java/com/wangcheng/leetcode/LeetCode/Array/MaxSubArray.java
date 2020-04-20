package com.wangcheng.leetcode.LeetCode.Array;

import com.sunfusheng.algo.common.util.LeetCodeUtil;
import com.sunfusheng.algo.common.util.LeetCodeUtilKt;

/**
 *【题目】
 * 53.最大子序和
 * 给定一个整数数组nums，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *【示例】
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *【进阶】
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 *
 * @author liwangcheng
 * @date 2020/3/24.
 */
public class MaxSubArray {

    /**
     * 方法一：分治法
     * 这个是使用分治法解决问题的典型的例子，
     * 并且可以用与合并排序相似的算法求解。
     * 下面是用分治法解决问题的模板：
     * * * * * * * * * * * * * * * * *
     * * 定义基本情况
     * * 将问题分解为子问题并递归地解决它们
     * * 合并子问题的解以获得原始问题的解
     * * * * * * * * * * * * * * * * *
     * 算法：
     * 当最大子数组有 n 个数字时：
     *  若 n==1，返回此元素。
     *  left_sum 为最大子数组前 n/2 个元素，在索引为 (left + right) / 2 的元素属于左子数组。
     *  right_sum 为最大子数组的右子数组，为最后 n/2 的元素。
     *  cross_sum 是包含左右子数组且含索引 (left + right) / 2 的最大值。
     *
     * 复杂度分析
     * 时间复杂度：O(NlogN)。
     * 空间复杂度：O(logN)，递归时栈使用的空间。
     *
     * PS：这种分治的思想需要掌握
     */
    public static int solution1(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    private static int helper(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }
        LeetCodeUtil.logln("helper - left = " + left + ", right = " + right);
        int p = (left + right) / 2;
        int leftSum = helper(nums, left, p);
        int rightSum = helper(nums, p + 1, right);
        int crossSum = crossSum(nums, left, right, p);
        return Math.max(Math.max(leftSum, rightSum), crossSum);
    }

    private static int crossSum(int[] nums, int left, int right, int p) {
        LeetCodeUtil.logln("crossSum - left = " + left + ", right = " + right + ", p = " + p);
        if (left == right) {
            return nums[left];
        }
        LeetCodeUtil.logln(LeetCodeUtilKt.format(nums));
        int leftSubSum = Integer.MIN_VALUE;
        int currSum = 0;
        for (int i = p; i > left - 1; i--) {
            currSum += nums[i];
            LeetCodeUtil.logln("currSum = " + currSum + ", nums[" + i + "] = " + nums[i]);
            leftSubSum = Math.max(leftSubSum, currSum);
            LeetCodeUtil.logln("leftSubSum = " + leftSubSum);
        }
        int rightSubSum = Integer.MIN_VALUE;
        currSum = 0;
        for (int i = p + 1; i < right + 1; i++) {
            currSum += nums[i];
            LeetCodeUtil.logln("currSum = " + currSum + ", nums[" + i + "] = " + nums[i]);
            rightSubSum = Math.max(rightSubSum, currSum);
            LeetCodeUtil.logln("rightSubSum = " + rightSubSum);
        }
        return leftSubSum + rightSubSum;
    }

    /**
     * 方法二：贪心
     * 使用单个数组作为输入来查找最大（或最小）元素（或总和）的问题，
     * 贪心算法是可以在线性时间解决的方法之一。
     *【思路】
     * 每一步都选择最佳方案，到最后就是全局最优的方案。
     *【算法】
     * 该算法通用且简单：
     * 遍历数组并在每个步骤中更新
     *  当前元素
     *  当前元素位置的最大和
     *  迄今为止的最大和
     *
     * 复杂度分析
     * 时间复杂度：O(N)，只遍历一次数组。
     * 空间复杂度：O(1)，只使用了常数空间。
     */
    public static int solution2(int[] nums) {
        int currSum = nums[0];
        int maxSum = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            LeetCodeUtil.logln("nums[" + i + "] = " + nums[i]);
            LeetCodeUtil.logln("before currSum = " + currSum);
            currSum = Math.max(nums[i], currSum + nums[i]);
            LeetCodeUtil.logln("after currSum = " + currSum);
            maxSum = Math.max(currSum, maxSum);
            LeetCodeUtil.logln("maxSum = " + maxSum);
        }
        return maxSum;
    }

    /**
     * 方法三：动态规划（Kadane 算法）
     *【算法】
     * 在整个数组或在固定大小的滑动窗口中找到总和或最大值或最小值的问题，
     * 可以通过动态规划（DP）在线性时间内解决。
     *
     * 有两种标准 DP 方法适用于数组：
     * 1.常数空间，沿数组移动并在原数组修改。
     * 2.线性空间，
     *  首先沿 left->right 方向移动，
     *  然后再沿 right->left 方向移动。
     *  合并结果。
     *
     * 在这里使用第一种方法，因为可以修改数组跟踪当前位置的最大和。
     * 下一步是在知道当前位置的最大和后更新全局最大和。
     *
     * 复杂度分析
     * 时间复杂度：O(N)，只遍历了一次数组。
     * 空间复杂度：O(1)，使用了常数的空间。
     */
    public static int solution3(int[] nums) {
        int maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > 0) {
                nums[i] += nums[i - 1];
            }
            maxSum = Math.max(maxSum, nums[i]);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution1([-2,1,-3,4,-1,2,1,-5,4]) = " + MaxSubArray.solution1(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
        LeetCodeUtil.logln("----------");
        LeetCodeUtil.logln("solution2([-2,1,-3,4,-1,2,1,-5,4]) = " + MaxSubArray.solution2(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
        LeetCodeUtil.logln("----------");
        LeetCodeUtil.logln("solution3([-2,1,-3,4,-1,2,1,-5,4]) = " + MaxSubArray.solution3(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }
}
