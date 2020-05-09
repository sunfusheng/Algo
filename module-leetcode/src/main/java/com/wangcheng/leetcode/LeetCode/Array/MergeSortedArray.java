package com.wangcheng.leetcode.LeetCode.Array;


import com.sunfusheng.algo.common.util.LeetCodeUtil;
import com.sunfusheng.algo.common.util.LeetCodeUtilKt;

import java.util.Arrays;

/**
 * 【题目】
 * 88.合并两个有序数组
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，
 * 使 num1 成为一个有序数组。
 * <p>
 * 说明:
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
 * 你可以假设 nums1 有足够的空间（大于或等于 m + n）来保存 nums2 中的元素。
 * <p>  
 * 【示例】
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * 输出: [1,2,2,3,5,6]
 *
 * @author liwangcheng
 * @date 2020/3/27.
 */
public class MergeSortedArray {

    /**
     * 方法一 : 合并后排序
     * <p>
     * 复杂度分析
     * 时间复杂度 : O((n+m)log(n+m))。
     * 空间复杂度 : O(1)。
     */
    public static void solution1(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
    }

    /**
     * 方法二 : 双指针 / 从前往后
     * 最直接的算法实现是将指针p1置为nums1的开头，p2为nums2的开头，
     * 在每一步将最小值放入输出数组中。
     * 由于nums1是用于输出的数组，需要将nums1中的前m个元素放在其他地方，
     * 也就需要 O(m) 的空间复杂度。
     * <p>
     * 复杂度分析
     * 时间复杂度 : O(n+m)。
     * 空间复杂度 : O(m)。
     */
    public static void solution2(int[] nums1, int m, int[] nums2, int n) {
        int[] tempNums1 = new int[m];
        System.arraycopy(nums1, 0, tempNums1, 0, m);
        int p1 = 0;
        int p2 = 0;
        int p = 0;
        while (p1 < m && p2 < n) {
            nums1[p++] = tempNums1[p1] < nums2[p2] ? tempNums1[p1++] : nums2[p2++];
        }
        if (p1 < m) {
            System.arraycopy(tempNums1, p1, nums1, p1 + p2, m + n - p1 - p2);
        }
        if (p2 < n) {
            System.arraycopy(nums2, p2, nums1, p1 + p2, m + n - p1 - p2);
        }
    }

    /**
     * 方法三 : 双指针 / 从后往前
     * 如果从结尾开始改写nums1的值又会如何呢？这里没有信息，因此不需要额外空间。
     * <p>
     * 复杂度分析
     * 时间复杂度 : O(n+m)。
     * 空间复杂度 : O(1)。
     */
    public static void solution3(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;
        int p2 = n - 1;
        int p = m + n - 1;
        while (p1 >= 0 && p2 >= 0) {
            nums1[p--] = nums1[p1] > nums2[p2] ? nums1[p1--] : nums2[p2--];
        }
        System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
    }

    public static void main(String[] args) {
        int[] num1 = new int[]{1, 3, 4, 0, 0, 0, 0};
        int[] num2 = new int[]{2, 3, 5, 7};
        MergeSortedArray.solution1(num1, 3, num2, 4);
        LeetCodeUtil.logln("solution1()" + LeetCodeUtilKt.format(num1));
        num1 = new int[]{1, 3, 4, 0, 0, 0, 0};
        MergeSortedArray.solution2(num1, 3, num2, 4);
        LeetCodeUtil.logln("solution2()" + LeetCodeUtilKt.format(num1));
        num1 = new int[]{1, 3, 4, 0, 0, 0, 0};
        MergeSortedArray.solution3(num1, 3, num2, 4);
        LeetCodeUtil.logln("solution3()" + LeetCodeUtilKt.format(num1));
    }
}
