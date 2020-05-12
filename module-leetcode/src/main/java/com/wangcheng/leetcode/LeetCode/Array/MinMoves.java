package com.wangcheng.leetcode.LeetCode.Array;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.Arrays;

/**
 *【题目】
 * 453.最小移动次数使数组元素相等
 * 给定一个长度为 n 的非空整数数组，找到让数组所有元素相等的最小移动次数。
 * 每次移动将会使 n - 1 个元素增加 1。
 *【示例】
 * 输入:
 * [1,2,3]
 * 输出:
 * 3
 * 解释:
 * 只需要3次移动（注意每次移动会增加两个元素的值）：
 * [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
 *
 * @author liwangcheng
 * @date 2020/5/12.
 */
public class MinMoves {

    /**
     * 方法一：暴力法
     * 首先,知道,为了在最小移动内使所有元素相等，需要在数组的最大元素
     * 之外的所有元素中执行增加。
     * 因此,在暴力法中，扫描整个数组以查找最大值和最小元素。
     * 此后，将 1 添加到除最大元素之外的所有元素，并增加移动数的计数。
     * 同样，重复相同的过程，直到最大元素和最小元素彼此相等。
     *
     * 复杂度分析
     * 时间复杂度：O(n^2 k)，其中 n 为数组长度，k 为最大值和最小值的差。
     * 空间复杂度：O(1)。不需要额外空间。
     */
    public static int solution1(int[] nums) {
        if (LeetCodeUtil.isEmpty(nums)) {
            return 0;
        }
        int min = 0;
        int max = nums.length - 1;
        int count = 0;
        while (true) {
            for (int i = 0; i < nums.length; i++) {
                if (nums[max] < nums[i]) {
                    max = i;
                }
                if (nums[min] > nums[i]) {
                    min = i;
                }
            }
            if (nums[max] == nums[min]) {
                break;
            }
            for (int i = 0; i < nums.length; i++) {
                if (i != max) {
                    nums[i]++;
                }
            }
            count++;
        }
        return count;
    }

    /**
     * 方法二：改进暴力法 【超时】
     * 算法
     * 在上一方法中，每一步对每个元素增加 1。可以在一定程度上改进这一方法。
     * 为了让最小元素等于最大元素，至少需要加 k 次。在那之后，最大元素可能
     * 发生变化。因此，一次性增加增量 k=max-min，并将移动次数增加 k。然后，
     * 对整个数组进行扫描，找到新的最大值和最小值，重复这一过程直到最大元素
     * 和最小元素相等。
     *
     * 复杂度分析
     * 时间复杂度：O(n^2)。每次迭代中两个元素是相等的。
     * 空间复杂度：O(1)。不需要额外空间。
     */
    public static int solution2(int[] nums) {
        if (LeetCodeUtil.isEmpty(nums)) {
            return 0;
        }
        int min = 0;
        int max = nums.length - 1;
        int count = 0;
        while (true) {
            for (int i = 0; i < nums.length; i++) {
                if (nums[max] < nums[i]) {
                    max = i;
                }
                if (nums[min] > nums[i]) {
                    min = i;
                }
            }
            int diff = nums[max] - nums[min];
            if (diff == 0) {
                break;
            }
            count += diff;
            for (int i = 0; i < nums.length; i++) {
                if (i != max) {
                    nums[i] += diff;
                }
            }
        }
        return count;
    }

    /**
     * 方法三：利用排序 【通过】
     * 算法
     * 如果对数组进行排序得到有序数列 a，可以有效地简化问题。
     * 类似于方法二，用 diff=max-min 更新数列。但不同的是，
     * 不需要每次都遍历整个数组来得到最大和最小值，而是可以
     * 利用数组的有序性在 O(1) 时间内找到更新后的最大值和
     * 最小值。此外，也不需要真的更新数组的值。
     *
     * 复杂度分析
     * 时间复杂度：O(nlog(n))。排序需要 O(nlog(n)) 的时间。
     * 空间复杂度：O(1)。不需要额外空间。
     */
    public static int solution3(int[] nums) {
        if (LeetCodeUtil.isEmpty(nums)) {
            return 0;
        }
        Arrays.sort(nums);
        int count = 0;
        for (int i = nums.length - 1; i > 0; i--) {
            count += nums[i] - nums[0];
        }
        return count;
    }

    /**
     * 方法四：动态规划 【通过】
     * 算法
     * 如果对数组进行排序得到有序数列 a，可以有效地简化问题。
     * 考虑有序数组 a，不考虑整个问题，而是将问题分解。假设，
     * 直到 i-1 位置的元素都已经相等，只需要考虑 i 位的元素，
     * 将差值 diff=a[i]-a[i-1] 加到总移动次数上，使得第 i
     * 位也相等。moves=moves+diff。
     *
     * 但当想要继续这一步时，a[i] 之后的元素也会被增加 diff，
     * 亦即 a[j]=a[j]+diff，其中 j>i。
     *
     * 但当实现本方法时，不需要对这样的a[j]进行增加。相反，把
     * moves 的数量增加到当前元素（a[i]）中，a'[i]=a[i]+moves。
     *
     * 简而言之，对数列进行排序，一直更新 moves 以使得直到
     * 当前的元素相等，而不改变除了当前元素之外的元素。在整个
     * 数组扫描完毕后，moves 即为答案。
     *
     * 复杂度分析
     * 时间复杂度：O(nlog(n))。 排序需要 O(nlog(n)) 的时间。
     * 空间复杂度：O(1)。只使用了一个变量。
     */
    public static int solution4(int[] nums) {
        if (LeetCodeUtil.isEmpty(nums)) {
            return 0;
        }
        Arrays.sort(nums);
        int moves = 0;
        for (int i = 1; i < nums.length; i++) {
            nums[i] += moves;
            int diff = nums[i] - nums[i - 1];
            moves += diff;
        }
        return moves;
    }

    /**
     * 方法五：数学法
     * 算法
     * 该方法基于以下思路：将除了一个元素之外的全部元素+1，
     * 等价于将该元素-1，因为只对元素的相对大小感兴趣。因此，
     * 该问题简化为需要进行的减法次数。
     * 显然，只需要将所有的数都减到最小的数即可。
     *
     * 复杂度分析
     * 时间复杂度：O(n)。对数组进行了一次遍历。
     * 空间复杂度：O(1)。不需要额外空间。
     */
    public static int solution5(int[] nums) {
        int moves = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            moves += nums[i];
            min = Math.min(min, nums[i]);
        }
        return moves - min * nums.length;
    }

    /**
     * TODO 记住这个
     * 方法六：改进的数学法
     * 上一个方法可能存在问题。求和可能会非常大，造成整数越界。
     * 为了避免这一问题，可以即时计算 moves。
     *
     * 复杂度分析
     * 时间复杂度：O(n)。一次遍历寻找最小值，一次遍历计算次数。
     * 空间复杂度：O(1)。不需要额外空间。
     */
    public static int solution6(int[] nums) {
        int moves = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            min = Math.min(min, nums[i]);
        }
        for (int i = 0; i < nums.length; i++) {
            moves += nums[i] - min;
        }
        return moves;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        LeetCodeUtil.logln("solution1([1,2,3]) = " + MinMoves.solution1(nums));
        nums = new int[]{1, 2, 3};
        LeetCodeUtil.logln("solution2([1,2,3]) = " + MinMoves.solution2(nums));
        nums = new int[]{1, 2, 3};
        LeetCodeUtil.logln("solution3([1,2,3]) = " + MinMoves.solution3(nums));
        nums = new int[]{1, 2, 3};
        LeetCodeUtil.logln("solution4([1,2,3]) = " + MinMoves.solution4(nums));
        nums = new int[]{1, 2, 3};
        LeetCodeUtil.logln("solution5([1,2,3]) = " + MinMoves.solution5(nums));
        nums = new int[]{1, 2, 3};
        LeetCodeUtil.logln("solution6([1,2,3]) = " + MinMoves.solution6(nums));
        nums = new int[]{1, 2, 3, 4};
        LeetCodeUtil.logln("solution1([1,2,3,4]) = " + MinMoves.solution1(nums));
        nums = new int[]{1, 2, 3, 4};
        LeetCodeUtil.logln("solution2([1,2,3,4]) = " + MinMoves.solution2(nums));
        nums = new int[]{1, 2, 3, 4};
        LeetCodeUtil.logln("solution3([1,2,3,4]) = " + MinMoves.solution3(nums));
        nums = new int[]{1, 2, 3, 4};
        LeetCodeUtil.logln("solution4([1,2,3,4]) = " + MinMoves.solution4(nums));
        nums = new int[]{1, 2, 3, 4};
        LeetCodeUtil.logln("solution5([1,2,3,4]) = " + MinMoves.solution5(nums));
        nums = new int[]{1, 2, 3, 4};
        LeetCodeUtil.logln("solution6([1,2,3,4]) = " + MinMoves.solution6(nums));
    }
}
