package com.wangcheng.leetcode.LeetCode.BinaryTree;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 【题目】
 * 104.二叉树的最大深度
 * 给定一个二叉树，找出其最大深度。
 * <p>
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * 【示例】
 * 给定二叉树 [3,9,20,null,null,15,7]，
 * <p>
 * --- 3
 * -- / \
 * - 9  20
 * --- /  \
 * -- 15   7
 * 返回它的最大深度 3
 *
 * @author liwangcheng
 * @date 2020/3/30.
 */
public class MaxDepth {

    private static class TreeNode {
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
     * 方法一：递归
     * <p>
     * 复杂度分析
     * 时间复杂度：每个结点只访问一次，因此时间复杂度为 O(N)，其中 N 是结点的数量。
     * 空间复杂度：在最糟糕的情况下，树是完全不平衡的，例如每个结点只剩下左子结点，
     * 递归将会被调用 N 次（树的高度），因此保持调用栈的存储将是 O(N)。
     * 但在最好的情况下（树是完全平衡的），树的高度将是 log(N)。
     * 因此，在这种情况下的空间复杂度将是 O(log(N))。
     */
    public static int solution1(TreeNode root) {
        if (null == root) {
            return 0;
        }
        int leftDepth = solution1(root.left);
        int rightDepth = solution1(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    /**
     * 方法二：广度优先遍历
     * 思路：
     * 使用 DFS 策略访问每个结点，同时在每次访问时更新最大深度
     * 算法：
     * 从包含根结点且相应深度为 1 的栈开始。
     * 然后继续迭代：将当前结点弹出栈并推入子结点。每一步都会更新深度。
     * <p>
     * 复杂度分析
     * 时间复杂度：O(N)。
     * 空间复杂度：O(N)。
     */
    public static int solution2(TreeNode root) {
        if (null == root) {
            return 0;
        }
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(root, 1));
        int depth = 0;
        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> current = queue.poll();
            root = current.first;
            int currentDepth = current.second;
            depth = Math.max(currentDepth, depth);
            if (root.left != null) {
                queue.add(new Pair<>(root.left, currentDepth + 1));
            }
            if (root.right != null) {
                queue.add(new Pair<>(root.right, currentDepth + 1));
            }
        }
        return depth;
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
