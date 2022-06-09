package com.wangcheng.leetcode.LeetCode.Array;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 78.子集
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * <p>
 * 示例 2：
 * 输入：nums = [0]
 * 输出：[[],[0]]
 *
 * @author sunfusheng
 * @since 2022/06/09
 */
public class SubSets {

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null) {
            return res;
        }
        if (nums.length == 0) {
            res.add(new ArrayList<>());
            return res;
        }
        LinkedList<Integer> path = new LinkedList<>();
        helper(nums, 0, path, res);
        return res;
    }

    private static void helper(int[] nums, int index, LinkedList<Integer> path, List<List<Integer>> res) {
        if (index == nums.length) {
            res.add(new LinkedList<>(path));
        } else {
            helper(nums, index + 1, path, res);
            path.add(nums[index]);
            helper(nums, index + 1, path, res);
            path.removeLast();
        }
    }
}
