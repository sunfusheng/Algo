package com.wangcheng.leetcode.LeetCode.BinaryTree;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.ArrayDeque;

/**
 *【题目】
 * 100.相同的树
 * 给定两个二叉树，编写一个函数来检验它们是否相同。
 * <p>
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 * <p>
 *【示例】
 * 示例 1:
 * 输入:------ 1         1
 * --------- / \       / \
 * -------- 2   3     2   3
 * ------- [1,2,3],  [1,2,3]
 * 输出: true
 *
 * 示例 2:
 * 输入:------ 1         1
 * --------- /           \
 * -------- 2             2
 * ------- [1,2],     [1,null,2]
 * 输出: false
 *
 * 示例 3:
 * 输入:------ 1         1
 * --------- / \       / \
 * -------- 2   1     1   2
 * ------- [1,2,1],  [1,1,2]
 * 输出: false
 *
 * @author liwangcheng
 * @date 2020/3/27.
 */
public class SameTree {

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
     * 最简单的策略是使用递归。首先判断 p 和 q 是不是 None，
     * 然后判断它们的值是否相等。
     * 若以上判断通过，则递归对子结点做同样操作。
     *
     * 复杂度分析
     *
     * 时间复杂度 : O(N)，其中 N 是树的结点数，因为每个结点都访问一次。
     * 空间复杂度 : 最优情况（完全平衡二叉树）时为 O(log(N))，
     *  最坏情况下（完全不平衡二叉树）时为 O(N)，用于维护递归栈。
     */
    public static boolean solution1(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }
        if (t1.val != t2.val) {
            return false;
        }
        return solution1(t1.left, t2.left) && solution1(t1.right, t2.right);
    }

    /**
     * 方法二：迭代
     * 从根开始，每次迭代将当前结点从双向队列中弹出。
     * 然后，进行方法一中的判断：
     * p 和 q 不是 None,
     * p.val 等于 q.val,
     * 若以上均满足，则压入子结点。
     */
    public static boolean solution2(TreeNode p, TreeNode q) {
        if (!check(p, q)) {
            return false;
        }
        ArrayDeque<TreeNode> deqP = new ArrayDeque<>();
        ArrayDeque<TreeNode> deqQ = new ArrayDeque<>();
        deqP.addLast(p);
        deqQ.addLast(q);
        while (!deqP.isEmpty()) {
            p = deqP.removeFirst();
            q = deqQ.removeFirst();
            if (!check(p, q)) {
                return false;
            }
            if (p != null) {
                if (!check(p.left, q.left)) {
                    return false;
                }
                if (p.left != null) {
                    deqP.addLast(p.left);
                    deqQ.addLast(q.left);
                }
                if (!check(p.right, q.right)) {
                    return false;
                }
                if (p.right != null) {
                    deqP.addLast(p.right);
                    deqQ.addLast(q.right);
                }
            }
        }
        return true;
    }

    private static boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (q == null || p == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        SameTree.TreeNode p = new SameTree.TreeNode(1);
        p.left = new SameTree.TreeNode(2);
        p.right = new SameTree.TreeNode(3);
        SameTree.TreeNode q = new SameTree.TreeNode(1);
        q.left = new SameTree.TreeNode(2);
        q.right = new SameTree.TreeNode(3);
        SameTree.TreeNode p2 = p;
        SameTree.TreeNode q2 = q;
        LeetCodeUtil.logln("solution1(p, q) = " + SameTree.solution1(p, q));
        LeetCodeUtil.logln("solution2(p, q) = " + SameTree.solution2(p2, q2));
        LeetCodeUtil.logln("==============================");
        p = new SameTree.TreeNode(1);
        p.left = new SameTree.TreeNode(2);
        p.right = new SameTree.TreeNode(3);
        p.left.left = new SameTree.TreeNode(4);
        q = new SameTree.TreeNode(1);
        q.left = new SameTree.TreeNode(2);
        q.right = new SameTree.TreeNode(3);
        p2 = p;
        q2 = q;
        LeetCodeUtil.logln("solution1(p, q) = " + SameTree.solution1(p, q));
        LeetCodeUtil.logln("solution2(p, q) = " + SameTree.solution2(p2, q2));
    }
}
