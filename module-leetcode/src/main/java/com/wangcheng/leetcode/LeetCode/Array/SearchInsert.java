package com.wangcheng.leetcode.LeetCode.Array;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 *【题目】
 * 35. 搜索插入位置
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * <p>
 * 你可以假设数组中无重复元素。
 * <p>
 *【示例】
 * 示例 1:
 * 输入: [1,3,5,6], 5
 * 输出: 2
 *
 * 示例 2:
 * 输入: [1,3,5,6], 2
 * 输出: 1
 *
 * @author liwangcheng
 * @date 2020/3/21.
 */
public class SearchInsert {

    /**
     * 暴力破解
     */
    public static int solution1(int[] nums, int target) {
        if (nums == null) {
            return -1;
        }
        for (int i = 0; i < nums.length; i++) {
            if (target <= nums[i]) {
                return i;
            }
        }
        return  - 1;
    }

    /**
     * 解题方案
     *【思路】
     * 标签：二分查找
     * 如果该题目暴力解决的话需要 O(n) 的时间复杂度，
     * 但是如果二分的话则可以降低到 O(logn) 的时间复杂度。
     * 整体思路和普通的二分查找几乎没有区别，
     * 先设定左侧下标 left 和右侧下标 right，再计算中间下标 mid。
     * 每次根据 nums[mid] 和 target 之间的大小进行判断，
     * 相等则直接返回下标，nums[mid] < target 则 left 右移，
     * nums[mid] > target 则 right 左移。
     * 查找结束如果没有相等值则返回 left，该值为插入位置。
     *
     * 时间复杂度：O(logn)
     *
     * 【注意】
     * 二分查找的思路不难理解，但是边界条件容易出错，
     * 比如循环结束条件中 left 和 right 的关系，
     * 更新 left 和 right 位置时要不要加 1 减 1。
     */

    /**
     * =============
     * 下面提供两个模板
     * =============
     */

    public static int solution2(int[] nums, int target) {
        if (nums == null) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public static int solution3(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {

        LeetCodeUtil.logln("solution1([1,2,3,4,5], 5) = " + SearchInsert.solution1(new int[]{1,2,3,4,5}, 5));
        LeetCodeUtil.logln("solution1([1,2,3,4,5], 4) = " + SearchInsert.solution1(new int[]{1,2,3,4,5}, 4));
        LeetCodeUtil.logln("solution1([1,2,3,4,5], 3) = " + SearchInsert.solution1(new int[]{1,2,3,4,5}, 3));
        LeetCodeUtil.logln("solution1([1,2,3,4,5], 2) = " + SearchInsert.solution1(new int[]{1,2,3,4,5}, 2));
        LeetCodeUtil.logln("solution1([1,2,3,4,5], 1) = " + SearchInsert.solution1(new int[]{1,2,3,4,5}, 1));
        LeetCodeUtil.logln("solution2([1,2,3,4,5], 5) = " + SearchInsert.solution2(new int[]{1,2,3,4,5}, 5));
        LeetCodeUtil.logln("solution2([1,2,3,4,5], 4) = " + SearchInsert.solution2(new int[]{1,2,3,4,5}, 4));
        LeetCodeUtil.logln("solution2([1,2,3,4,5], 3) = " + SearchInsert.solution2(new int[]{1,2,3,4,5}, 3));
        LeetCodeUtil.logln("solution2([1,2,3,4,5], 2) = " + SearchInsert.solution2(new int[]{1,2,3,4,5}, 2));
        LeetCodeUtil.logln("solution2([1,2,3,4,5], 1) = " + SearchInsert.solution2(new int[]{1,2,3,4,5}, 1));
        LeetCodeUtil.logln("solution3([1,2,3,4,5], 4) = " + SearchInsert.solution3(new int[]{1,2,3,4,5}, 5));
        LeetCodeUtil.logln("solution3([1,2,3,4,5], 5) = " + SearchInsert.solution3(new int[]{1,2,3,4,5}, 4));
        LeetCodeUtil.logln("solution3([1,2,3,4,5], 3) = " + SearchInsert.solution3(new int[]{1,2,3,4,5}, 3));
        LeetCodeUtil.logln("solution3([1,2,3,4,5], 2) = " + SearchInsert.solution3(new int[]{1,2,3,4,5}, 2));
        LeetCodeUtil.logln("solution3([1,2,3,4,5], 1) = " + SearchInsert.solution3(new int[]{1,2,3,4,5}, 1));
    }
}
