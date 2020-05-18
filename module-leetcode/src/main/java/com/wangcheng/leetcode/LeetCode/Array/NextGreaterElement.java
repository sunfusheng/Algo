package com.wangcheng.leetcode.LeetCode.Array;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 *【题目】
 * 496.下一个更大元素 I
 * 给定两个没有重复元素的数组 nums1 和 nums2，其中nums1 是 nums2 的子集。
 * 找到 nums1 中每个元素在 nums2 中的下一个比其大的值。
 * <p>
 * nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的
 * 第一个比 x 大的元素。如果不存在，对应位置输出 -1 。
 * <p>
 *【示例】
 * 示例 1:
 * 输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
 * 输出: [-1,3,-1]
 * 解释:
 *     对于num1中的数字4，你无法在第二个数组中找到下一个更大的数字，因此输出 -1。
 *     对于num1中的数字1，第二个数组中数字1右边的下一个较大数字是 3。
 *     对于num1中的数字2，第二个数组中没有下一个更大的数字，因此输出 -1。
 *
 * 示例 2:
 * 输入: nums1 = [2,4], nums2 = [1,2,3,4].
 * 输出: [3,-1]
 * 解释:
 *     对于 num1 中的数字 2 ，第二个数组中的下一个较大数字是 3 。
 *     对于 num1 中的数字 4 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
 *【提示】
 * nums1和nums2中所有元素是唯一的。
 * nums1和nums2的数组大小都不超过1000。
 *
 * @author liwangcheng
 * @date 2020/5/18.
 */
public class NextGreaterElement {

    /**
     * 方法：单调栈+Map
     *
     * 复杂度分析
     * 时间复杂度：O(M+N)，其中 M 和 N 分别是数组 nums1 和 nums2 的长度。
     * 空间复杂度：O(N)。在遍历 nums2 时，需要使用栈，以及哈希映射用来临时存储答案。
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static int[] solution(int[] findNums, int[] nums) {
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();
        int[] res = new int[findNums.length];
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[i] > stack.peek()) {
                map.put(stack.pop(), nums[i]);
            }
            stack.push(nums[i]);
        }
        while (!stack.isEmpty()) {
            map.put(stack.pop(), -1);
        }
        for (int i = 0; i < findNums.length; i++) {
            res[i] = map.getOrDefault(findNums[i], -1);
        }
        return res;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String[] args) {
        LeetCodeUtil.logln("solution([4,1,2],[1,3,4,2]) = " + Arrays.toString(NextGreaterElement.solution(new int[]{4, 1, 2}, new int[]{1, 3, 4, 2})));
        LeetCodeUtil.logln("solution([4,1,2],[1,3,4,2]) = " + Arrays.toString(NextGreaterElement.solution(new int[]{2, 4}, new int[]{1, 2, 3, 4})));
    }
}
