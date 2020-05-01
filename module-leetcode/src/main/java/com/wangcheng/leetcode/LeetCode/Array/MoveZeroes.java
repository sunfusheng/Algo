package com.wangcheng.leetcode.LeetCode.Array;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.Arrays;

/**
 *【题目】
 * 283.移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，
 * 同时保持非零元素的相对顺序。
 *【示例】
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 *【说明】
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 *
 * @author liwangcheng
 * @date 2020/5/1.
 */
public class MoveZeroes {

    /**
     * 问题的两个要求是：
     * - 将所有 0 移动到数组末尾。
     * - 所有非零元素必须保持其原始顺序。
     *
     * 这里很好地认识到这两个需求是相互排斥的，也就是说，
     * 你可以解决单独的子问题，
     * 然后将它们组合在一起以得到最终的解决方案。
     */

    /**
     * 方法一：空间局部优化
     * 复杂度分析
     * 时间复杂度：O(n)。但是，操作总数是局部优化的。可以在更少的操作中实现相同的结果。
     * 空间复杂度：O(n)，创建 “temp” 数组来存储结果。
     */
    public static void solution1(int[] nums) {
        if (LeetCodeUtil.isEmpty(nums)) {
            return;
        }
        int[] temp = new int[nums.length];
        for (int i = 0, j = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                temp[j++] = nums[i];
            }
        }
        System.arraycopy(temp,0,nums,0,nums.length);
    }

    /**
     * 方法二：空间最优，操作局部优化（双指针）
     *
     * 复杂度分析
     * 时间复杂度：O(n)。但是，操作仍然是局部优化的。代码执行的总操作（数组写入）为 n（元素总数）。
     * 空间复杂度：O(1)。只使用常量空间。
     */
    public static void solution2(int[] nums) {
        int lastNonZeroFoundAt = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[lastNonZeroFoundAt++] = nums[i];
            }
        }
        for (int i = lastNonZeroFoundAt; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    /**
     * 方法三：最优解
     *
     * 复杂度分析
     * 时间复杂度：O(n)。但是，操作是最优的。代码执行的总操作（数组写入）是非 0 元素的数量。
     *  这比上一个解决方案的复杂性（当大多数元素为 0 时）要好得多。但是，两种算法的最坏情况
     *  （当所有元素都为非 0 时）复杂性是相同的。
     * 空间复杂度：O(1)。只使用了常量空间。
     */
    public static void solution3(int[] nums) {
        int lastNonZeroFoundAt = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                swap(nums, lastNonZeroFoundAt++, i);
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,1,0,3,12};
        LeetCodeUtil.logln(Arrays.toString(nums));
        MoveZeroes.solution1(nums);
        LeetCodeUtil.logln("solution1() = " + Arrays.toString(nums));
        nums = new int[]{0,1,0,3,12};
        MoveZeroes.solution2(nums);
        LeetCodeUtil.logln("solution2() = " + Arrays.toString(nums));
        nums = new int[]{0,1,0,3,12};
        MoveZeroes.solution3(nums);
        LeetCodeUtil.logln("solution3() = " + Arrays.toString(nums));
    }
}
