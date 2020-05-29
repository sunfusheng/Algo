package com.wangcheng.leetcode.LeetCode.BinaryTree;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 *【题目】
 * 563.二叉树的坡度
 * 给定一个二叉树，计算整个树的坡度。
 * <p>
 * 一个树的节点的坡度定义即为，该节点左子树的结点之和和右子树
 * 结点之和的差的绝对值。空结点的的坡度是0。
 * <p>
 * 整个树的坡度就是其所有节点的坡度之和。
 *【示例】
 * 输入:
 *
 * ---- 1
 * -- /   \
 * - 2     3
 *
 * 输出: 1
 * 解释:
 * 结点的坡度 2 : 0
 * 结点的坡度 3 : 0
 * 结点的坡度 1 : |2-3| = 1
 * 树的坡度 : 0 + 0 + 1 = 1
 *【注意】
 * 任何子树的结点的和不会超过32位整数的范围。
 * 坡度的值不会超过32位整数的范围。
 *
 * @author liwangcheng
 * @date 2020/5/29.
 */
public class FindTilt {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 方法：递归
     * 算法
     * 从问题的描述中，可以清楚地了解到，需要在给定树的每个
     * 结点处找到其坡度，并将所有的坡度相加以获得最终结果。
     * 要找出任意结点的坡度，需要求出该结点的左子树上所有结点
     * 和以及其右子树上全部结点和的差值。
     *
     * 因此，为了找出解决方案，使用递归函数 traverse，在
     * 任何结点调用该函数，都会返回当前结点下面（包括其自身）
     * 的结点和。借助于任何结点的左右子结点的这一和值，可以
     * 直接获得该结点所对应的坡度。
     *
     * 复杂度分析
     * 时间复杂度：O(n)，其中 n 是结点的数目。每个结点访问一次。
     * 空间复杂度：O(n)，在最糟糕的情形下，当树倾斜时，
     *  树的深度可以达到 n。平均情况下，树的深度为 logn。
     */
    private static int tilt = 0;
    public static int solution(TreeNode root) {
        traverse(root);
        return tilt;
    }

    private static int traverse(TreeNode root) {
        if (null == root) {
            return 0;
        }
        int left = traverse(root.left);
        int right = traverse(root.right);
        tilt += Math.abs(left - right);
        return left + right + root.val;
    }

    public static void main(String[] args) {
        FindTilt.TreeNode root = new FindTilt.TreeNode(1);
        root.left = new FindTilt.TreeNode(2);
        root.right = new FindTilt.TreeNode(3);
        LeetCodeUtil.logln("solution() = " + FindTilt.solution(root));
    }
}
