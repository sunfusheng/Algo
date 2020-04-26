package com.wangcheng.leetcode.LeetCode.BinaryTree;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 【题目】
 * 107.二叉树的层次遍历 II
 * 给定一个二叉树，返回其节点值自底向上的层次遍历。
 * <p>
 * （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 * <p>
 * 【例如】
 * 给定二叉树 [3,9,20,null,null,15,7],
 * <p>
 * --- 3
 * -- / \
 * - 9  20
 * --- /  \
 * -- 15   7
 * <p>
 * 返回其自底向上的层次遍历为：
 * [
 * - [15,7],
 * - [9,20],
 * - [3]
 * ]
 *
 * @author liwangcheng
 * @date 2020/3/30.
 */
public class LevelOrderBottom {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void solution1(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode node;
        LeetCodeUtil.logln("————层序遍历————");
        while (!queue.isEmpty()) {
            node = queue.poll();
            LeetCodeUtil.logln(" - " + node.val);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

    public static List<List<Integer>> solution2(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int count = queue.size();
            List<Integer> list = new ArrayList<>();
            while (count > 0) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                count--;
            }
            res.add(0, list);
        }
        return res;
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
        solution1(p);
        solution1(q);
        List<List<Integer>> list = solution2(p2);
        LeetCodeUtil.logln("levelOrder");
        for (List<Integer> item : list) {
            LeetCodeUtil.logln(item.toString());
        }
        list = solution2(q2);
        for (List<Integer> item : list) {
            LeetCodeUtil.logln(item.toString());
        }
    }
}
