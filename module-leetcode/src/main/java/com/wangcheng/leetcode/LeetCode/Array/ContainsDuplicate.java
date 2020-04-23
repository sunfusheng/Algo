package com.wangcheng.leetcode.LeetCode.Array;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *【题目】
 * 217.存在重复元素
 * 给定一个整数数组，判断是否存在重复元素。
 * <p>
 * 如果任意一值在数组中出现至少两次，函数返回 true。
 * 如果数组中每个元素都不相同，则返回 false 。
 * <p>
 *【示例】
 * 输入: [1,2,3,1]
 * 输出: true
 *
 * @author liwangcheng
 * @date 2020/4/23.
 */
public class ContainsDuplicate {

    /**
     * 方法一：朴素线性查找
     * 循环不变式是指在每次迭代前和后均保持不变的性质。
     * 下面就是循环不变式:
     *  - 在下一次搜索之前,搜索过的整数中没有重复的整数。
     * 循环不变式在循环之前为真，因为还没有搜索过的整数。
     * 每次循环，查找当前元素的任何可能重复。如果发现重复项,
     * 则函数返回 True 退出；如果没有发现，则不变式仍然成立。
     * 因此，如果循环结束，循环不变式说明全部 n 个整数中不存在重复元素。
     *
     * 复杂度分析
     * 时间复杂度 : O(n^2)。
     * 空间复杂度 : O(1)。只使用了常数额外空间。
     */
    public static boolean solution1(int[] nums) {
        if (LeetCodeUtil.isEmpty(nums)) {
            return false;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] == nums[i]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 方法二：排序
     * 直觉
     * 如果存在重复元素，排序后它们应该相邻。
     *
     * 复杂度分析
     * 时间复杂度 : O(nlogn)。
     * 排序的复杂度是 O(nlogn)，扫描的复杂度是 O(n)。整个算法主要由排序过程决定，因此是 O(nlogn)。
     * 空间复杂度 : O(1)。
     * 这取决于具体的排序算法实现，通常而言，使用 堆排序 的话，是 O(1)。
     *
     * 注意
     * 此处的算法实现对原始数组进行排序，修改了原始数组。
     * 通常，除非调用方清楚输入数据将被修改，否则不应该随意修改输入数据。
     * 可以先复制 nums，然后对副本进行操作。
     */
    public static boolean solution2(int[] nums) {
        if (LeetCodeUtil.isEmpty(nums)) {
            return false;
        }
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] == nums[i]) {
                return true;
            }
        }
        return false;
    }

    /**
     * 方法三：哈希表
     * 利用支持快速搜索和插入操作的动态数据结构。
     *
     * 复杂度分析
     * 时间复杂度 : O(n)。
     * search() 和 insert() 各自使用 n 次，每个操作耗费常数时间。
     * 空间复杂度 : O(n)。哈希表占用的空间与元素数量是线性关系。
     */
    public static boolean solution3(int[] nums) {
        if (LeetCodeUtil.isEmpty(nums)) {
            return false;
        }
        Set<Integer> set = new HashSet<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1,2,3,1};
        int[] nums2 = new int[]{1,2,3,4,5};
        int[] nums3 = new int[]{1,1,1,3,3,4,3,2,4,2};
        LeetCodeUtil.logln(Arrays.toString(nums1));
        LeetCodeUtil.logln("solution1 = " + ContainsDuplicate.solution1(nums1));
        LeetCodeUtil.logln("solution2 = " + ContainsDuplicate.solution2(nums1));
        LeetCodeUtil.logln("solution3 = " + ContainsDuplicate.solution3(nums1));
        LeetCodeUtil.logln(Arrays.toString(nums2));
        LeetCodeUtil.logln("solution1 = " + ContainsDuplicate.solution1(nums2));
        LeetCodeUtil.logln("solution2 = " + ContainsDuplicate.solution2(nums2));
        LeetCodeUtil.logln("solution3 = " + ContainsDuplicate.solution3(nums2));
        LeetCodeUtil.logln(Arrays.toString(nums3));
        LeetCodeUtil.logln("solution1 = " + ContainsDuplicate.solution1(nums3));
        LeetCodeUtil.logln("solution2 = " + ContainsDuplicate.solution2(nums3));
        LeetCodeUtil.logln("solution3 = " + ContainsDuplicate.solution3(nums3));
    }
}
