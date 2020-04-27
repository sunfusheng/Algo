package com.wangcheng.leetcode.LeetCode.BinaryTree;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 【题目】
 * 226.翻转二叉树
 * 翻转一棵二叉树。
 * 【示例】
 * 输入：
 * <p>
 * ------ 4
 * ---- /   \
 * --- 2     7
 * -- / \   / \
 * - 1   3 6   9
 * 输出：
 * <p>
 * ------ 4
 * ---- /   \
 * --- 7     2
 * -- / \   / \
 * - 9   6 3   1
 * <p>
 * 备注:
 * 这个问题是受到 Max Howell 的 原问题 启发的 ：
 * 谷歌：我们90％的工程师使用您编写的软件(Homebrew)，
 * 但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。
 *
 * @author liwangcheng
 * @date 2020/4/24.
 */
public class InvertTree {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 方法一（递归）
     */
    public static TreeNode solution1(TreeNode root) {
        if (null == root) {
            return null;
        }
        TreeNode left = solution1(root.left);
        TreeNode right = solution1(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    /**
     * 方法二（迭代）
     * 思路:
     * 需要交换树中所有节点的左孩子和右孩子。因此可以创一个队列来存储
     * 所有左孩子和右孩子还没有被交换过的节点。开始的时候，只有根节点
     * 在这个队列里面。只要这个队列不空，就一直从队列中出队节点，然后
     * 互换这个节点的左右孩子节点，接着再把孩子节点入队到队列，对于其
     * 中的空节点不需要加入队列。最终队列一定会空，这时候所有节点的孩
     * 子节点都被互换过了，直接返回最初的根节点就可以了。
     */
    public static TreeNode solution2(TreeNode root) {
        if (null == root) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            TreeNode temp = cur.left;
            cur.left = cur.right;
            cur.right = temp;
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        testSolution1();
        testSolution2();
    }

    private static void testSolution1() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);
        TreeNode resTree = InvertTree.solution1(root);
        LeetCodeUtil.logln("root - " + resTree.val);
        LeetCodeUtil.logln("left - " + resTree.left.val);
        LeetCodeUtil.logln("right - " + resTree.right.val);
        LeetCodeUtil.logln("left-left - " + resTree.left.left.val);
        LeetCodeUtil.logln("left-right - " + resTree.left.right.val);
        LeetCodeUtil.logln("right-left - " + resTree.right.left.val);
        LeetCodeUtil.logln("right-right - " + resTree.right.right.val);
    }

    private static void testSolution2() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);
        TreeNode resTree = InvertTree.solution2(root);
        LeetCodeUtil.logln("root - " + resTree.val);
        LeetCodeUtil.logln("left - " + resTree.left.val);
        LeetCodeUtil.logln("right - " + resTree.right.val);
        LeetCodeUtil.logln("left-left - " + resTree.left.left.val);
        LeetCodeUtil.logln("left-right - " + resTree.left.right.val);
        LeetCodeUtil.logln("right-left - " + resTree.right.left.val);
        LeetCodeUtil.logln("right-right - " + resTree.right.right.val);
    }

}
