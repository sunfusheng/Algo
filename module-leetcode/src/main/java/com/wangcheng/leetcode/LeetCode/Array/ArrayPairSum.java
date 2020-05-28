package com.wangcheng.leetcode.LeetCode.Array;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.Arrays;

/**
 *【题目】
 * 561.数组拆分 I
 * 给定长度为 2n 的数组, 你的任务是将这些数分成 n 对,
 * 例如 (a1, b1), (a2, b2), ..., (an, bn) ，
 * 使得从 1 到 n 的 min(ai, bi) 总和最大。
 *【示例】
 * 输入: [1,4,3,2]
 * 输出: 4
 * 解释: n 等于 2, 最大总和为 4 = min(1, 2) + min(3, 4).
 *【提示】
 * n 是正整数,范围在 [1, 10000].
 * 数组中的元素范围在 [-10000, 10000].
 *
 * @author liwangcheng
 * @date 2020/5/28.
 */
public class ArrayPairSum {

    /**
     * 方法一 暴力求解
     *
     * 复杂度分析
     * 时间复杂度：O(n!)。对于数组中的 n 元素，总共可以 n 排列。
     * 空间复杂度：O(1)。仅需使用常数级的额外空间。
     */
    private static int max_sum = Integer.MIN_VALUE;
    public static int solution1(int[] nums) {
        permute(nums, 0);
        return max_sum;
    }

    private static void permute(int[] nums, int l) {
        if (l == nums.length - 1) {
            int sum = 0;
            for (int i = 0; i < nums.length / 2; i++) {
                sum += Math.min(nums[i], nums[nums.length / 2 + i]);
            }
            max_sum = Math.max(max_sum, sum);
        }
        for (int i = l; i < nums.length; i++) {
            swap(nums, i, l);
            permute(nums, l + 1);
            swap(nums, i, l);
        }
    }

    private static void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }

    /**
     * 方法二 排序
     *
     * 复杂度分析
     * 时间复杂度：O(nlog(n))。排序需要 O(nlog(n)) 的时间。另外会有一次数组的遍历。
     * 空间复杂度：O(1)。仅仅需要常数级的空间.
     */
    public static int solution2(int[] nums) {
        Arrays.sort(nums);
        int maxSum = 0;
        for (int i = 0; i < nums.length; i+=2) {
            maxSum += nums[i];
        }
        return maxSum;
    }

    /**
     * TODO
     * 方法三 使用额外的空间
     *
     * 复杂度分析
     * 时间复杂度：O(n)。需要遍历一次哈希表 arr。
     * 空间复杂度：O(n)。存储一个大小为nn哈希表 arr 所需要的空间。
     *
     * 参考：
     * https://leetcode-cn.com/problems/array-partition-i/solution/shu-zu-chai-fen-i-by-leetcode/
     */
    public static int solution3(int[] nums) {
        int[] arr = new int[20001];
        int lim = 10000;
        for (int num: nums) {
            arr[num + lim]++;
        }
        int d = 0, sum = 0;
        for (int i = -10000; i <= 10000; i++) {
            sum += (arr[i + lim] + 1 - d) / 2 * i;
            d = (2 + arr[i + lim] - d) % 2;
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,4,3,2};
        LeetCodeUtil.logln("solution1(1,4,3,2) = " + ArrayPairSum.solution1(nums));
        LeetCodeUtil.logln("solution2(1,4,3,2) = " + ArrayPairSum.solution2(nums));
        LeetCodeUtil.logln("solution3(1,4,3,2) = " + ArrayPairSum.solution3(nums));
    }

}
