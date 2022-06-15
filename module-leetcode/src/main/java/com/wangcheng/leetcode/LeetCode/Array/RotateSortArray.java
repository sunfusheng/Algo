package com.wangcheng.leetcode.LeetCode.Array;

import com.sunfusheng.algo.common.util.AlgoUtil;

/**
 * 33.搜索旋转排序数组
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * 例如：
 * 数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] 。
 * <p>
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1。
 * 你可以假设数组中不存在重复的元素。
 * <p>
 * 注意：
 * 你的算法时间复杂度必须是 O(logn) 级别。
 * <p>
 * 示例 1:
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * <p>
 * 示例 2:
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 *
 * @author sunfusheng
 * @since 2020/4/28
 */
public class RotateSortArray {

    /**
     * 解法：二分搜索
     * <p>
     * 时间复杂度：O(log n)
     * 空间复杂度：O(1)
     */
    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[l] <= nums[mid]) {
                if (nums[l] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[r]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4, 5, 6, 7, 0, 1, 2};
        System.out.print("输入：");
        AlgoUtil.printlnArray(nums);

        System.out.println("搜索6，输出：" + search(nums, 6));
        System.out.println("搜索7，输出：" + search(nums, 7));
        System.out.println("搜索1，输出：" + search(nums, 1));
        System.out.println("搜索3，输出：" + search(nums, 3));
    }
}
