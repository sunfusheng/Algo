package com.wangcheng.leetcode.LeetCode.BinaryTree;

import java.util.Stack;

/**
 *【题目】
 * 404.左叶子之和
 * 计算给定二叉树的所有左叶子之和。
 *【示例】
 *
 * ---- 3
 * --- / \
 * -- 9  20
 * ---- /  \
 * --- 15   7
 *
 * 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
 *
 * @author liwangcheng
 * @date 2020/5/8.
 */
public class SumOfLeftLeaves {


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private static class Pair<F, S> {
        F first;
        S second;

        public Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }
    }

    /**
     * 方法一：递归遍历
     */
    private static int n = 0;
    public static int solution1(TreeNode root) {
        return sumOfLeftLeaves1(root);
    }

    private static int sumOfLeftLeaves1(TreeNode root) {
        if (null == root) {
            return 0;
        }
        if (root.left != null && (root.left.left != null || root.left.right != null)) {
            sumOfLeftLeaves1(root.left);
        }
        if (root.left != null && root.left.left == null && root.left.right == null) {
            n += root.left.val;
        }
        if (root.right != null && (root.right.left != null || root.right.right != null)) {
            sumOfLeftLeaves1(root.right);
        }
        return n;
    }

    /**
     * 方法二：递归
     */
    private static int result = 0;
    public static int solution2(TreeNode root) {
        return sumOfLeftLeaves2(root);
    }

    private static int sumOfLeftLeaves2(TreeNode root) {
        if(root == null){
            return 0;
        }
        // 是否是当前节点的左孩子
        // 当前节点的左孩子是不是叶子节点（叶子结点：没有左右孩子）
        if(root.left != null && (root.left.left == null && root.left.right == null)){
            result = result +root.left.val;
        }
        sumOfLeftLeaves2(root.left);
        sumOfLeftLeaves2(root.right);
        return result;
    }

    /**
     * 方法三：迭代
     */
    public static int solution3(TreeNode root) {
        if (null == root) {
            return 0;
        }
        Stack<Pair<TreeNode, Boolean>> stack = new Stack<>();
        stack.push(new Pair<>(root, false));
        int sum = 0;
        while (!stack.isEmpty()) {
            Pair<TreeNode, Boolean> pair = stack.pop();
            root = pair.first;
            boolean flag = pair.second;
            if (flag && root.left == null && root.right == null) {
                sum += root.val;
            }
            if (root.left != null) {
                stack.push(new Pair<>(root.left, true));
            }
            if (root.right != null) {
                stack.push(new Pair<>(root.right, false));
            }
        }
        return sum;
    }
}
