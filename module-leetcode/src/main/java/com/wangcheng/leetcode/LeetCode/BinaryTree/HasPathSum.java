package com.wangcheng.leetcode.LeetCode.BinaryTree;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.LinkedList;

/**
 * 【题目】
 * 112.路径总和
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 【示例】 
 * 给定如下二叉树，以及目标和 sum = 22，
 * <p>
 * ------- 5
 * ------ / \
 * ----- 4   8
 * ---- /   / \
 * --- 11  13  4
 * -- /  \      \
 * - 7    2      1
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 *
 * @author liwangcheng
 * @date 2020/4/1.
 */
public class HasPathSum {

    private static class TreeNode {
        int valve;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            valve = x;
        }
    }

    /**
     * 方法 1：递归
     * 最直接的方法就是利用递归
     * 遍历整棵树：如果当前节点不是叶子，对它的所有孩子节点，
     * 递归调用 hasPathSum 函数，其中 sum 值减去当前节点的权值；
     * 如果当前节点是叶子，检查 sum 值是否为 0，也就是是否找到了给定的目标和。
     * <p>
     * 复杂度分析
     * 时间复杂度：访问每个节点一次，时间复杂度为 O(N) ，其中 N 是节点个数。
     * 空间复杂度：最坏情况下，整棵树是非平衡的，例如每个节点都只有一个孩子，
     * 递归会调用 N 次（树的高度），因此栈的空间开销是 O(N) 。
     * 但在最好情况下，树是完全平衡的，高度只有 log(N)，
     * 因此在这种情况下空间复杂度只有 O(log(N)) 。
     */
    public static boolean solution1(TreeNode root, int sum) {
        if (null == root) {
            return false;
        }
        sum -= root.valve;
        if (null == root.left && null == root.right) {
            return sum == 0;
        }
        return solution1(root.left, sum) || solution1(root.right, sum);
    }

    /**
     * 方法 2：迭代
     * 可以用栈将递归转成迭代的形式
     * <p>
     * 深度优先搜索在除了最坏情况下都比广度优先搜索更快。
     * 最坏情况是指满足目标和的 root->leaf 路径是最后被考虑的，
     * 这种情况下深度优先搜索和广度优先搜索代价是相通的。
     * 【思路】
     * 利用深度优先策略访问每个节点，同时更新剩余的目标和。
     * <p>
     * 复杂度分析
     * 时间复杂度：和递归方法相同是 O(N)。
     * 空间复杂度：当树不平衡的最坏情况下是 O(N) 。在最好情况（树是平衡的）下是 O(logN)。
     */
    public static boolean solution2(TreeNode root, int sum) {
        if (null == root) {
            return false;
        }
        LinkedList<TreeNode> nodeStack = new LinkedList<>();
        LinkedList<Integer> sumStack = new LinkedList<>();
        nodeStack.add(root);
        sumStack.add(sum - root.valve);
        TreeNode node;
        int currentSum = 0;
        while (!nodeStack.isEmpty()) {
            node = nodeStack.pollLast();
            currentSum = sumStack.pollLast();
            if (null == node.left && null == node.right && currentSum == 0) {
                return true;
            }
            if (null != node.right) {
                nodeStack.add(node.right);
                sumStack.add(currentSum - node.right.valve);
            }
            if (null != node.left) {
                nodeStack.add(node.left);
                sumStack.add(currentSum - node.left.valve);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        HasPathSum.TreeNode root = new HasPathSum.TreeNode(1);
        root.left = new HasPathSum.TreeNode(2);
        root.left.left = new HasPathSum.TreeNode(4);
        root.left.left.left = new HasPathSum.TreeNode(6);
        root.right = new HasPathSum.TreeNode(3);
        root.right.right = new HasPathSum.TreeNode(5);
        root.right.right.right = new HasPathSum.TreeNode(7);
        LeetCodeUtil.logln("solution1(root, 13) = " + HasPathSum.solution1(root, 13));
        LeetCodeUtil.logln("solution1(root, 16) = " + HasPathSum.solution1(root, 16));
        LeetCodeUtil.logln("solution2(root, 13) = " + HasPathSum.solution2(root, 13));
        LeetCodeUtil.logln("solution2(root, 16) = " + HasPathSum.solution2(root, 16));
    }
}
