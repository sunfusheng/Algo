package com.wangcheng.leetcode.LeetCode.Array;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 349.两个数组的交集
 * 给定两个数组，编写一个函数来计算它们的交集。
 *【示例】
 * 示例 1:
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2]
 *
 * 示例 2:
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [9,4]
 *【说明】
 * 输出结果中的每个元素一定是唯一的。
 * 我们可以不考虑输出结果的顺序。
 *
 * @author liwangcheng
 * @date 2020/5/5.
 */
public class Intersection {

    /**
     * 方法一：两个 set
     * 将两个数组转换为集合 set，然后迭代较小的集合检查是否存在在较大集合中。
     * 平均情况下，这种方法的时间复杂度为 O(n+m)。
     *
     * 复杂度分析
     * 时间复杂度：O(m+n)，其中 n 和 m 是数组的长度。
     *  O(n) 的时间用于转换 nums1 在集合中，
     *  O(m) 的时间用于转换 nums2 到集合中，
     *  并且平均情况下，集合的操作为 O(1)。
     * 空间复杂度：O(m+n)，最坏的情况是数组中的所有元素都不同。
     */
    public static int[] solution1(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            set1.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            set2.add(nums2[i]);
        }
        if (set1.size() < set2.size()) {
            return helper1(set1, set2);
        } else {
            return helper1(set2, set1);
        }
    }

    private static int[] helper1(Set<Integer> set1, Set<Integer> set2) {
        List<Integer> list = new ArrayList<>();
        for (Integer i : set1) {
            if (set2.contains(i)) {
                list.add(i);
            }
        }
        int[] nums = new int[list.size()];
        for (int i = 0, len = list.size(); i < len; i++) {
            nums[i] = list.get(i);
        }
        return nums;
    }

    /**
     * 方法二：内置函数
     *
     * 复杂度分析
     * 时间复杂度：一般情况下是 O(m+n)，最坏情况下是 O(m×n)。
     * 空间复杂度：最坏的情况是 O(m+n)，当数组中的元素全部不一样时。
     */
    public static int[] solution2(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            set1.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            set2.add(nums2[i]);
        }
        set1.retainAll(set2);
        int[] nums = new int[set1.size()];
        int index = 0;
        for (Integer i : set1) {
            nums[index++] = i;
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1,2,2,1};
        int[] nums2 = new int[]{2,2};
        LeetCodeUtil.logln("nums1 = " + Arrays.toString(nums1));
        LeetCodeUtil.logln("nums2 = " + Arrays.toString(nums2));
        LeetCodeUtil.logln("solution1() = " + Arrays.toString(Intersection.solution1(nums1, nums2)));
        LeetCodeUtil.logln("solution2() = " + Arrays.toString(Intersection.solution2(nums1, nums2)));
        nums1 = new int[]{4,9,5};
        nums2 = new int[]{9,4,9,8,4};
        LeetCodeUtil.logln("nums1 = " + Arrays.toString(nums1));
        LeetCodeUtil.logln("nums2 = " + Arrays.toString(nums2));
        LeetCodeUtil.logln("solution1() = " + Arrays.toString(Intersection.solution1(nums1, nums2)));
        LeetCodeUtil.logln("solution2() = " + Arrays.toString(Intersection.solution2(nums1, nums2)));
    }
}
