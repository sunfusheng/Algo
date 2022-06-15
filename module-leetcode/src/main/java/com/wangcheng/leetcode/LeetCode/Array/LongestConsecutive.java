package com.wangcheng.leetcode.LeetCode.Array;

import java.util.HashSet;
import java.util.Set;

/**
 * 128.最长连续序列
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * <p>
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * <p>
 * 示例 1：
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * <p>
 * 示例 2：
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 *
 * @author sunfusheng
 * @since 2022/06/15
 */
public class LongestConsecutive {

    /**
     * 算法思想：哈希表
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int longest = 0;
        for (int num : set) {
            if (!set.contains(num - 1)) {
                int cur = num;
                int max = 1;
                while (set.contains(cur + 1)) {
                    cur += 1;
                    max++;
                }
                longest = Math.max(longest, max);
            }
        }
        return longest;
    }
}
