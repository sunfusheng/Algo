package com.wangcheng.leetcode.LeetCode.Array;

import android.annotation.SuppressLint;

import java.util.HashMap;
import java.util.Map;

/**
 * 560. 和为 K 的子数组
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
 * <p>
 * 示例 1：
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 * <p>
 * 示例 2：
 * 输入：nums = [1,2,3], k = 3
 * 输出：2
 *
 * @author sunfusheng
 * @since 2022/06/02
 */
public class SubArraySum {

    /**
     * 方法一：枚举
     * <p>
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     */
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int count = 0;
        for (int start = 0; start < nums.length; start++) {
            int sum = 0;
            for (int end = start; end >= 0; end--) {
                sum += nums[end];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 方法二：前缀和 + 哈希表优化
     * <p>
     * 思路和算法
     * 我们定义 pre[i] 为 [0..i] 里所有数的和，则 pre[i] 可以由 pre[i−1] 递推而来，即：
     * pre[i] = pre[i−1] + nums[i]
     * <p>
     * 那么 [j..i] 这个子数组和为 k 这个条件我们可以转化为:
     * pre[i] − pre[j−1] == k
     * <p>
     * 简单移项可得符合条件的下标 j 需要满足:
     * pre[j−1] == pre[i] − k
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    @SuppressLint("NewApi")
    public int subarraySum2(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int count = 0;
        int pre = 0;
        for (int num : nums) {
            pre += num;
            count += map.getOrDefault(pre - k, 0);
            map.put(pre, map.getOrDefault(pre, 0) + 1);
        }
        return count;
    }
}
