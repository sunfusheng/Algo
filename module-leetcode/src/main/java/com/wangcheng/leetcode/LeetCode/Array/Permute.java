package com.wangcheng.leetcode.LeetCode.Array;

import com.sunfusheng.algo.common.util.AlgoUtil;

import java.util.ArrayList;
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
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length < 2) {
            return null;
        }

        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        backtrack(nums, path, res);
        return res;
    }

    private static void backtrack(int[] nums, LinkedList<Integer> path, List<List<Integer>> res) {
        if (nums.length == path.size()) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int num : nums) {
            if (path.contains(num)) {
                continue;
            }
            path.addLast(num);
            backtrack(nums, path, res);
            path.removeLast();
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
