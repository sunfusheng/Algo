package com.wangcheng.leetcode.LeetCode.Array;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.Arrays;
import java.util.Stack;

/**
 * 【题目】
 * 581.最短无序连续子数组
 * 给定一个整数数组，你需要寻找一个连续的子数组，如果对
 * 这个子数组进行升序排序，那么整个数组都会变为升序排序。
 * <p>
 * 你找到的子数组应是最短的，请输出它的长度。
 * <p>
 * 【示例】
 * 输入: [2, 6, 4, 8, 10, 9, 15]
 * 输出: 5
 * 解释: 你只需要对 [6, 4, 8, 10, 9] 进行升序排序，
 * 那么整个表都会变为升序排序。
 * 【说明】
 * 输入的数组长度范围在 [1, 10,000]。
 * 输入的数组可能包含重复元素 ，所以升序的意思是<=。
 *
 * @author liwangcheng
 * @date 2020/6/4.
 */
public class FindUnsortedSubArray {

    /**
     * TODO
     * 方法 1：暴力
     * <p>
     * 复杂度分析
     * 时间复杂度：O(n^3)。使用了三重循环。
     * 空间复杂度：O(1) 。只使用了常数空间。
     */
    public static int solution1(int[] nums) {
        int res = nums.length;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j <= nums.length; j++) {
                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;
                int prev = Integer.MIN_VALUE;
                for (int k = i; k < j; k++) {
                    min = Math.min(min, nums[k]);
                    max = Math.max(max, nums[k]);
                }
                if ((i > 0 && nums[i - 1] > min) || (j < nums.length && nums[j] < max)) {
                    continue;
                }
                int k = 0;
                while (k < i && prev <= nums[k]) {
                    prev = nums[k];
                    k++;
                }
                if (k != i) {
                    continue;
                }
                k = j;
                while (k < nums.length && prev <= nums[k]) {
                    prev = nums[k];
                    k++;
                }
                if (k == nums.length) {
                    res = Math.min(res, j - i);

                }
            }
        }
        return res;
    }

    /**
     * 方法 2：更好的暴力
     * <p>
     * 复杂度分析
     * 时间复杂度：O(n^2)。使用了两重循环。
     * 空间复杂度：O(1)。只使用了常数空间。
     */
    public static int solution2(int[] nums) {
        int l = nums.length;
        int r = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[i]) {
                    r = Math.max(r, j);
                    l = Math.min(l, i);
                }
            }
        }
        return r - l < 0 ? 0 : r - l + 1;
    }

    /**
     * 方法 3：排序
     * 算法
     * 一个简单的想法是：将数组 nums 进行排序，记为 nums_sorted。
     * 然后比较 nums 和 nums_sorted 的元素来决定最左边和最右边
     * 不匹配的元素。它们之间的子数组就是要求的最短无序子数组。
     * <p>
     * 复杂度分析
     * 时间复杂度：O(nlogn) 。排序消耗 nlogn 的时间。
     * 空间复杂度：O(n) 。拷贝了一份原数组来进行排序。
     */
    public static int solution3(int[] nums) {
        int[] snums = nums.clone();
        Arrays.sort(snums);
        int start = snums.length;
        int end = 0;
        for (int i = 0; i < snums.length; i++) {
            if (snums[i] != nums[i]) {
                start = Math.min(start, i);
                end = Math.max(end, i);
            }
        }
        return end < start ? 0 : end - start + 1;
    }

    /**
     * 方法 4：使用栈
     * 算法
     * 这个方法背后的想法仍然是选择排序。需要找到无序子数组
     * 中最小元素和最大元素分别对应的正确位置，来求得想要的
     * 无序子数组的边界。
     * <p>
     * 复杂度分析
     * 时间复杂度：O(n)。需要遍历数组一遍，栈的时间复杂度也为 O(n)。
     * 空间复杂度：O(n)。栈的大小最大达到 n。
     */
    public static int solution4(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int l = nums.length;
        int r = 0;
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[i] < nums[stack.peek()]) {
                l = Math.min(l, stack.pop());
            }
            stack.push(i);
        }
        stack.clear();
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                r = Math.max(r, stack.pop());
            }
            stack.push(i);
        }
        return r < l ? 0 : r - l + 1;
    }

    /**
     * 方法 5：不使用额外空间
     * 算法
     * - 这个算法背后的思想是无序子数组中最小元素的正确位置
     * 可以决定左边界，最大元素的正确位置可以决定右边界。
     * - 因此，首先需要找到原数组在哪个位置开始不是升序的。
     * 从头开始遍历数组，一旦遇到降序的元素，记录最小元素为 min 。
     * - 类似的，逆序扫描数组 nums，当数组出现升序的时候，
     * 记录最大元素为 max。
     * - 然后，再次遍历 nums 数组并通过与其他元素进行比较，
     * 来找到 min 和 max 在原数组中的正确位置。只需要从头
     * 开始找到第一个大于 min 的元素，从尾开始找到第一个小
     * 于 max 的元素，它们之间就是最短无序子数组。
     * <p>
     * 复杂度分析
     * 时间复杂度：O(n)。使用了 3 个 O(n) 的循环。
     * 空间复杂度：O(1)。使用了常数空间。
     */
    public static int solution5(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            // 找到逆序pair中的最小和最大值
            if (nums[i] < nums[i - 1]) {
                min = Math.min(min, nums[i]);
                max = Math.max(max, nums[i - 1]);
            }
        }
        // 找到逆序的起点和终点
        int l;
        int r;
        for (l = 0; l < nums.length; l++) {
            if (min < nums[l]) {
                break;
            }
        }
        for (r = nums.length - 1; r >= 0; r--) {
            if (max > nums[r]) {
                break;
            }
        }
        return r < l ? 0 : r - l + 1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 6, 4, 8, 10, 9, 15};
        LeetCodeUtil.logln("solution1() = " + solution1(nums));
        LeetCodeUtil.logln("solution2() = " + solution2(nums));
        LeetCodeUtil.logln("solution3() = " + solution3(nums));
        LeetCodeUtil.logln("solution4() = " + solution4(nums));
        LeetCodeUtil.logln("solution5() = " + solution5(nums));
        nums = new int[]{5, 2, 6, 4, 8, 10, 9, 15, 1};
        LeetCodeUtil.logln("solution1() = " + solution1(nums));
        LeetCodeUtil.logln("solution2() = " + solution2(nums));
        LeetCodeUtil.logln("solution3() = " + solution3(nums));
        LeetCodeUtil.logln("solution4() = " + solution4(nums));
        LeetCodeUtil.logln("solution5() = " + solution5(nums));
    }
}
