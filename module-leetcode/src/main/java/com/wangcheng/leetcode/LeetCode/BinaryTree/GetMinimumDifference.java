package com.wangcheng.leetcode.LeetCode.BinaryTree;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 *【题目】
 * 530.二叉搜索树的最小绝对差
 * 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意
 * 两节点的差的绝对值的最小值。
 *【示例】
 * 输入：
 *
 * --- 1
 * ---- \
 * ----- 3
 * ---- /
 * --- 2
 *
 * 输出：
 * 1
 *
 *【解释】
 * 最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
 *【提示】
 * 树中至少有 2 个节点。
 *
 * @author liwangcheng
 * @date 2020/5/22.
 */
public class GetMinimumDifference {

    private static int pre;
    private static int minDif;

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 思路
     * 通过中序遍历二叉搜索树得到的关键码序列是一个递增序列。
     * 这是二叉搜索树的一个重要性质，巧妙利用这一性质可以解决一系列二叉搜索树问题。
     *
     * 参考：
     * https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/solution/zhong-xu-bian-li-tuan-mie-xi-lie-er-cha-sou-suo-sh/
     */
    public static int solution(TreeNode root) {
        pre = -1;
        minDif = Integer.MAX_VALUE;
        inOrder(root);
        return minDif;
    }

    private static void inOrder(TreeNode root) {
        if (null == root) {
            return;
        }
        inOrder(root.left);
        if (pre != -1) {
            minDif = Math.min(minDif, root.val - pre);
        }
        pre = root.val;
        inOrder(root.right);
    }

    public static void main(String[] args) {
        GetMinimumDifference.TreeNode root = new GetMinimumDifference.TreeNode(1);
        root.right = new GetMinimumDifference.TreeNode(3);
        root.right.left = new GetMinimumDifference.TreeNode(2);
        LeetCodeUtil.logln("solution() = " + GetMinimumDifference.solution(root));
    }
}
