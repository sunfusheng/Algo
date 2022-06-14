package com.wangcheng.leetcode.LeetCode.Array;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.Arrays;

/**
 * 198.打家劫舍
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
 * 影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 * <p>
 * 示例 1:
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 * 偷窃到的最高金额 = 1 + 3 = 4 。
 * <p>
 * 示例 2:
 * 输入: [2,7,9,3,1]
 * 输出: 12
 * 解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，
 * 接着偷窃 5 号房屋 (金额 = 1)。偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 *
 * @author liwangcheng
 * @date 2020/4/20.
 */
public class Rob {

    /**
     * 思路：动态规划（官方）
     * f(k) = max(f(k – 2) + A_k, f(k – 1))
     * 选择 f(–1) = f(0) = 0 为初始情况，这将极大地简化代码。
     * 答案为 f(n)。可以用一个数组来存储并计算结果。
     * 不过由于每一步只需要前两个最大值，两个变量就足够用了。
     * <p>
     * 复杂度分析
     * 时间复杂度：O(n)。其中 n 为房子的数量。
     * 空间复杂度：O(1)。
     */
    public static int solution1(int[] nums) {
        int prevMax = 0;
        int currMax = 0;
        for (int i = 0; i < nums.length; i++) {
            int temp = currMax;
            currMax = Math.max(currMax, prevMax + nums[i]);
            prevMax = temp;
        }
        return currMax;
    }

    /**
     * 思路：动态规划
     * 动态规划方程：dp[n] = MAX(dp[n-1], dp[n-2] + num)
     * 由于不可以在相邻的房屋闯入，所以在当前位置 n 房屋可盗窃的最大值，
     * 要么就是 n-1 房屋可盗窃的最大值，
     * 要么就是 n-2 房屋可盗窃的最大值加上当前房屋的值，
     * ==>二者之间取最大值
     * 举例来说：
     * 1 号房间可盗窃最大值为 3 即为 dp[1]=3，
     * 2 号房间可盗窃最大值为 4 即为 dp[2]=4，
     * 3 号房间自身的值为 2 即为 num=2，
     * 那么 dp[3] = MAX(dp[2], dp[1] + num) = MAX(4, 3+2) = 5，
     * 3 号房间可盗窃最大值为 5
     * 时间复杂度：O(n)，n 为数组长度
     */
    public static int solution2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }
        int[] dp = new int[len];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[len - 1];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 9, 3, 1};
        LeetCodeUtil.logln(Arrays.toString(nums));
        LeetCodeUtil.logln("solution1() = " + solution1(nums));
        LeetCodeUtil.logln("solution2() = " + solution2(nums));
    }
}
