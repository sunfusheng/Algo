package com.wangcheng.leetcode.LeetCode.Array;

import com.sunfusheng.algo.common.util.LeetCodeUtil;
import com.sunfusheng.algo.common.util.LeetCodeUtilKt;

import java.util.Arrays;

/**
 * 【题目】
 * 167.两数之和 II - 输入有序数组
 * 给定一个已按照升序排列的有序数组，找到两个数使得它们相加之和等于目标数。
 * <p>
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 * <p>
 * 【说明】
 * 返回的下标值（index1 和 index2）不是从零开始的。
 * 可以假设每个输入只对应唯一的答案，而且不可以重复使用相同的元素。
 * <p>
 * 【示例】
 * 输入: numbers = [2, 7, 11, 15], target = 9
 * 输出: [1,2]
 * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 *
 * @author liwangcheng
 * @date 2020/4/8.
 */
public class TwoSumII {

    /**
     * 双指针法
     * 使用两个指针，初始分别位于第一个元素和最后一个元素位置，
     * 比较这两个元素之和与目标值的大小。如果和等于目标值，发现了这个唯一解。
     * 如果比目标值小，将较小元素指针增加一。如果比目标值大，将较大指针减小一。
     * 移动指针后重复上述比较知道找到答案。
     * <p>
     * 复杂度分析
     * 时间复杂度：O(n)。每个元素最多被访问一次，共有 n 个元素。
     * 空间复杂度：O(1)。只是用了两个指针。
     */
    public static int[] solution(int[] nums, int target) {
        int[] res = new int[]{-1, -1};
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                res[0] = left + 1;
                res[1] = right + 1;
                return res;
            } else if (sum > target) {
                right--;
            } else {
                left++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        LeetCodeUtil.logln(LeetCodeUtilKt.format(nums));
        LeetCodeUtil.logln("soluiton(nums, 5) = " + Arrays.toString(solution(nums, 5)));
        LeetCodeUtil.logln("soluiton(nums, 6) = " + Arrays.toString(solution(nums, 6)));
        LeetCodeUtil.logln("soluiton(nums, 7) = " + Arrays.toString(solution(nums, 7)));
        LeetCodeUtil.logln("soluiton(nums, 8) = " + Arrays.toString(solution(nums, 8)));
        LeetCodeUtil.logln("soluiton(nums, 9) = " + Arrays.toString(solution(nums, 9)));
        LeetCodeUtil.logln("soluiton(nums, 10) = " + Arrays.toString(solution(nums, 10)));
        LeetCodeUtil.logln("soluiton(nums, 11) = " + Arrays.toString(solution(nums, 11)));
        LeetCodeUtil.logln("soluiton(nums, 12) = " + Arrays.toString(solution(nums, 12)));
    }
}
