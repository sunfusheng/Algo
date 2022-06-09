package com.wangcheng.leetcode.LeetCode.Array;

import com.sunfusheng.algo.common.util.AlgoUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 【题目】
 * 46.全排列
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * <p>
 * 示例:
 * 输入: [1,2,3]
 * 输出:
 * [
 * - [1,2,3],
 * - [1,3,2],
 * - [2,1,3],
 * - [2,3,1],
 * - [3,1,2],
 * - [3,2,1]
 * ]
 *
 * @author sunfusheng
 * @since 2020/4/30
 */
public class Permute {

    /**
     * 回溯法
     * <p>
     * 时间复杂度：O(n*n!)
     * 空间复杂度：O(n)
     */
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        if (nums.length == 1) {
            List<Integer> list = new LinkedList<>();
            list.add(nums[0]);
            res.add(list);
            return res;
        }
        LinkedList<Integer> path = new LinkedList<>();
        helper(nums, path, res);
        return res;
    }

    private static void helper(int[] nums, LinkedList<Integer> path, List<List<Integer>> res) {
        if (nums.length == path.size()) {
            res.add(new LinkedList<>(path));
        } else {
            for (int num : nums) {
                if (path.contains(num)) {
                    continue;
                }
                path.add(num);
                helper(nums, path, res);
                path.removeLast();
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        System.out.print("输入：");
        AlgoUtil.printlnArray(nums);

        List<List<Integer>> res = permute(nums);
        System.out.println("输出：");
        AlgoUtil.printList2(res);
    }
}
