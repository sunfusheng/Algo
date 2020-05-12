package com.wangcheng.leetcode.LeetCode.Array;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *【题目】
 * 448.找到所有数组中消失的数字
 * 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，
 * 数组中的元素一些出现了两次，另一些只出现一次。
 * <p>
 * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
 * <p>
 * 能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗?
 * 可以假定返回的数组不算在额外空间内。
 * <p>
 *【示例】
 * 输入:
 * [4,3,2,7,8,2,3,1]
 * 输出:
 * [5,6]
 *
 * @author liwangcheng
 * @date 2020/5/12.
 */
public class FindDisappearedNumbers {

    /**
     * 方法一：使用哈希表
     *
     * 复杂度分析
     * 时间复杂度：O(N)。
     * 空间复杂度：O(N)。
     */
    public static List<Integer> solution1(int[] nums) {
        if (LeetCodeUtil.isEmpty(nums)) {
            return Collections.emptyList();
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i <= nums.length; i++) {
            if (!set.contains(i)) {
                res.add(i);
            }
        }
        return res;
    }

    /**
     * 方法二：原地修改
     * 需要知道数组中存在的数字，由于数组的元素取值范围是
     * [1, N]，所以可以不使用额外的空间去解决它。
     * 可以在输入数组本身以某种方式标记已访问过的数字，
     * 然后再找到缺失的数字。
     *
     * 算法：
     * - 遍历输入数组的每个元素一次。
     * - 将把 |nums[i]|-1 索引位置的元素标记为负数。即 nums[nums[i] - 1]×−1 。
     * - 然后遍历数组，若当前数组元素 nums[i] 为负数，说明在数组中存在数字 i+1。
     *
     * 复杂度分析
     * 时间复杂度：O(N)。
     * 空间复杂度：O(1)，因为在原地修改数组，没有使用额外的空间。
     */
    public static List<Integer> solution2(int[] nums) {
        if (LeetCodeUtil.isEmpty(nums)) {
            return Collections.emptyList();
        }
        for (int i = 0; i < nums.length; i++) {
            int newIndex = Math.abs(nums[i]) - 1;
            if (newIndex < nums.length && nums[newIndex] > 0) {
                nums[newIndex] *= -1;
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > 0) {
                res.add(i);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4,3,2,7,8,2,3,1};
        LeetCodeUtil.logln("solution1(4,3,2,7,8,2,3,1) = " + FindDisappearedNumbers.solution1(nums));
        LeetCodeUtil.logln("solution2(4,3,2,7,8,2,3,1) = " + FindDisappearedNumbers.solution2(nums));
        nums = new int[]{9,3,2,7,8,2,3,1};
        LeetCodeUtil.logln("solution1(9,3,2,7,8,2,3,1) = " + FindDisappearedNumbers.solution1(nums));
        LeetCodeUtil.logln("solution2(9,3,2,7,8,2,3,1) = " + FindDisappearedNumbers.solution2(nums));
    }
}
