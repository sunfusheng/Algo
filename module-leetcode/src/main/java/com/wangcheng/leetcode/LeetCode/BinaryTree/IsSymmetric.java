package com.wangcheng.leetcode.LeetCode.BinaryTree;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.LinkedList;
import java.util.Queue;

/**
 *【题目】
 * 101.对称二叉树
 * 给定一个二叉树，检查它是否是镜像对称的。
 *【示例】
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 * --- 1
 * -- / \
 * - 2   2
 *  / \ / \
 * 3  4 4  3
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 * --- 1
 * -- / \
 * - 2   2
 * -- \   \
 * -- 3    3
 * 说明:
 * 如果你可以运用递归和迭代两种方法解决这个问题，会很加分。
 *
 * @author liwangcheng
 * @date 2020/3/27.
 */
public class IsSymmetric {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 方法一：递归
     * 如果一个树的左子树与右子树镜像对称，那么这个树是对称的。
     *
     * 复杂度分析
     * 时间复杂度：O(n)，因为遍历整个输入树一次，所以总的运行时间为 O(n)，其中 n 是树中结点的总数。
     * 空间复杂度：递归调用的次数受树的高度限制。在最糟糕情况下，树是线性的，其高度为 O(n)。
     * 因此，在最糟糕的情况下，由栈上的递归调用造成的空间复杂度为 O(n)。
     */
    public static boolean solution1(TreeNode root) {
        if (null == root) {
            return true;
        }
        return isMirror(root.left, root.right);
    }

    private static boolean isMirror(TreeNode left, TreeNode right) {
        if (null == left && null == right) {
            return true;
        }
        if (null == left || null == right) {
            return false;
        }
        return left.val == right.val
                && isMirror(left.right, right.left)
                && isMirror(left.left, right.right);
    }

    /**
     * 方法二：迭代
     * 可以利用队列进行迭代。
     * 队列中每两个连续的结点应该是相等的，而且它们的子树互为镜像。
     * 最初，队列中包含的是 root 以及 root。该算法的工作原理类似于 BFS，
     * 但存在一些关键差异。每次提取两个结点并比较它们的值。
     * 然后，将两个结点的左右子结点按相反的顺序插入队列中。
     * 当队列为空时，或者检测到树不对称（即从队列中取出两个不相等的连续结点）
     * 时，该算法结束。
     */
    public static boolean solution2(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode t1 = q.poll();
            TreeNode t2 = q.poll();
            if (t1 == null && t2 == null) {
                continue;
            }
            if (t1 == null || t2 == null) {
                return false;
            }
            if (t1.val != t2.val) {
                return false;
            }
            // 注意入队顺序：镜像入队列
            q.add(t1.left);
            q.add(t2.right);
            q.add(t1.right);
            q.add(t2.left);
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode p = new TreeNode(1);
        p.left = new TreeNode(2);
        p.left.left = new TreeNode(3);
        p.left.right = new TreeNode(4);
        p.right = new TreeNode(2);
        p.right.left = new TreeNode(4);
        p.right.right = new TreeNode(3);
        TreeNode q = new TreeNode(1);
        q.left = new TreeNode(2);
        q.left.left = new TreeNode(3);
        q.right = new TreeNode(2);
        q.right.left = new TreeNode(3);
        TreeNode p2 = p;
        TreeNode q2 = q;
        LeetCodeUtil.logln("solution1(p) = " + solution1(p));
        LeetCodeUtil.logln("solution1(q) = " + solution1(q));
        LeetCodeUtil.logln("solution2(p2) = " + solution2(p2));
        LeetCodeUtil.logln("solution2(q2) = " + solution2(q2));
    }
}
