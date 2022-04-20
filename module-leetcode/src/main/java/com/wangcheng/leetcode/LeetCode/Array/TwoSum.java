package com.wangcheng.leetcode.LeetCode.Array;

import com.sunfusheng.algo.common.util.LeetCodeUtil;
import com.sunfusheng.algo.common.util.LeetCodeUtilKt;

import java.util.HashMap;
import java.util.Map;

/**
 * 【题目】
 * 1.两数之和
 * <p>
 * 给定一个整数数组 nums 和一个目标值 target，
 * 请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * <p>
 * 【示例】
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 返回 [0, 1]
 * 【要求】
 * 1、时间复杂度：O(n)
 * 2、空间复杂度：O(n)
 *
 * @author liwangcheng
 * @date 2020/3/10.
 */
public class TwoSum {

    /**
     * 方法一：暴力法
     * 遍历每个元素x，查找是否存在一个值与target-x相等
     * <p>
     * 时间复杂度：O(n2)
     * 空间复杂度：O(1)
     */
    public static int[] solution1(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] == target - arr[i]) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * 方法二：两遍哈希表
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public static int[] solution2(int[] arr, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], i);
        }

        for (int i = 0; i < arr.length; i++) {
            int next = target - arr[i];
            if (map.containsKey(next) && map.get(next) != i) {
                return new int[]{i, map.get(next)};
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * 方法三：一遍哈希表
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public static int[] solution3(int[] arr, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int next = target - arr[i];
            if (map.containsKey(next) && map.get(next) != i) {
                return new int[]{i, map.get(next)};
            }
            map.put(arr[i], i);
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        LeetCodeUtil.logln("nums = [2, 7, 11, 15]");

        String res = LeetCodeUtilKt.format(solution1(nums, 13));
        LeetCodeUtil.logln("solution1(13) -> " + res);

        res = LeetCodeUtilKt.format(solution2(nums, 9));
        LeetCodeUtil.logln("solution2(9) -> " + res);

        res = LeetCodeUtilKt.format(solution3(nums, 17));
        LeetCodeUtil.logln("solution3(17) -> " + res);

        res = LeetCodeUtilKt.format(solution3(nums, 19));
        LeetCodeUtil.logln("solution3(19) -> " + res);
    }
}
