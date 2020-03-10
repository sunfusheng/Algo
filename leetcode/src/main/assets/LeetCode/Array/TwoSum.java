package com.cheng.leetcode.LeetCode.Array;

import android.icu.text.UFormat;

import com.cheng.leetcode.Utils;
import com.cheng.leetcode.UtilsKt;

import java.util.HashMap;
import java.util.Map;

/**
 * 【题目】
 * 给定一个整数数组 nums 和一个目标值 target，
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

    private final int[] nums = new int[]{2, 7, 11, 15};

    /**
     * 方法一：暴力法
     * 遍历每个元素x，查找是否存在一个值与target-x相等
     * 时间复杂度：O(n2)
     * 空间复杂度：O(1)
     */
    private int[] solution1(int[] arr, int target) {
        int[] result = new int[2];
        for (int i = 0; i < arr.length; i++) {
            int pre = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                int next = target - pre;
                if (arr[j] == next) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }

    /**
     * 方法二：两遍哈希表
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    private int[] solution2(int[] arr, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], i);
        }
        for (int i = 0; i < arr.length; i++) {
            int next = target - arr[i];
            if (map.containsKey(next) && map.get(next) != i) {
                result[0] = i;
                result[1] = map.get(next);
                return result;
            }
        }
        return result;
    }

    /**
     * 方法三：一遍哈希表
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    private int[] solution3(int[] arr, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int next = target - arr[i];
            if (map.containsKey(next) && map.get(next) != i) {
                result[0] = i;
                result[1] = map.get(next);
                return result;
            }
            map.put(arr[i], i);
        }
        return result;
    }

    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        Utils.log("nums = [2, 7, 11, 15]");
        Utils.log("solution1(13) - " + UtilsKt.format(twoSum.solution1(twoSum.nums, 13)));
        Utils.log("solution2(9) - " + UtilsKt.format(twoSum.solution2(twoSum.nums, 9)));
        Utils.log("solution3(17) - " + UtilsKt.format(twoSum.solution3(twoSum.nums, 17)));
    }

}
