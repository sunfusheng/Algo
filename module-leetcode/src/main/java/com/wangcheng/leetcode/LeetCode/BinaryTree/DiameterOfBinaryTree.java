package com.wangcheng.leetcode.LeetCode.BinaryTree;

import com.sunfusheng.algo.common.model.TreeNode;
import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 * 【题目】
 * 543.二叉树的直径
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意
 * 两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 * 【示例】
 * 给定二叉树
 * <p>
 * ----- 1
 * ---- / \
 * --- 2   3
 * -- / \
 * - 4   5
 * <p>
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 * 【注意】
 * 两结点之间的路径长度是以它们之间边的数目表示。
 *
 * @author liwangcheng
 * @date 2020/5/23.
 */
public class DiameterOfBinaryTree {

    /**
     * 深度优先搜索
     * 一条路径的长度为该路径经过的节点数减一，所以求直径（即求
     * 路径长度的最大值）等效于求路径经过节点数的最大值减一。
     * <p>
     * 复杂度分析
     * 时间复杂度：O(N)，其中 N 为二叉树的节点数，即遍历一棵
     * 二叉树的时间复杂度，每个结点只被访问一次。
     * 空间复杂度：O(Height)，其中 Height 为二叉树的高度。
     * 由于递归函数在递归过程中需要为每一层递归函数分配栈空间，
     * 所以这里需要额外的空间且该空间取决于递归的深度，而递归
     * 的深度显然为二叉树的高度，并且每次递归调用的函数里又只
     * 用了常数个变量，所以所需空间复杂度为 O(Height) 。
     */
    public static int solution(TreeNode root) {
        if (null == root) {
            return 0;
        }
        int[] res = {1};
        depth(root, res);
        return res[0] - 1;
    }

    private static int depth(TreeNode root, int[] path) {
        if (null == root) {
            return 0;
        }
        int leftDepth = depth(root.left, path);
        int rightDepth = depth(root.right, path);
        path[0] = Math.max(path[0], leftDepth + rightDepth + 1);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        LeetCodeUtil.logln("solution() = " + solution(root));
    }
}
