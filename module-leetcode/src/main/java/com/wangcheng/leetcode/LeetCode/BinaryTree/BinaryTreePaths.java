package com.wangcheng.leetcode.LeetCode.BinaryTree;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.LinkedList;
import java.util.List;

/**
 *【题目】
 * 257.二叉树的所有路径
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 *【示例】
 *
 * 输入:
 *
 * ---- 1
 * -- /   \
 * - 2     3
 * -- \
 * --- 5
 *
 * 输出: ["1->2->5", "1->3"]
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 *
 * @author liwangcheng
 * @date 2020/4/29.
 */
public class BinaryTreePaths {
    
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
     * 最直观的方法是使用递归。在递归遍历二叉树时，需要考虑当前的节点和它的孩子节点。
     * 如果当前的节点不是叶子节点，则在当前的路径末尾添加该节点，并递归遍历该节点的
     * 每一个孩子节点。如果当前的节点是叶子节点，则在当前的路径末尾添加该节点后，
     * 就得到了一条从根节点到叶子节点的路径，可以把该路径加入到答案中。
     *
     * 复杂度分析
     * 时间复杂度：每个节点只会被访问一次，因此时间复杂度为 O(N)，其中 N 表示节点数目。
     * 空间复杂度：O(N)。这里不考虑存储答案 paths 使用的空间，仅考虑额外的空间复杂度。
     *  额外的空间复杂度为递归时使用的栈空间，在最坏情况下，当二叉树中每个节点只有一个
     *  孩子节点时，递归的层数为 N，此时空间复杂度为 O(N)。在最好情况下，当二叉树为
     *  平衡二叉树时，它的高度为 log(N)，此时空间复杂度为 O(log(N))。
     */
    public static List<String> solution1(TreeNode root) {
        LinkedList<String> paths = new LinkedList();
        constructPaths(root, "", paths);
        return paths;
    }

    private static void constructPaths(TreeNode root, String path, LinkedList<String> paths) {
        if (null != root) {
            path += root.val;
            // 当前节点是叶子节点
            if (null == root.left && null == root.right) {
                paths.add(path);
            } else {
                path += "->";
                // 当前节点不是叶子节点，继续递归遍历
                constructPaths(root.left, path, paths);
                constructPaths(root.right, path, paths);
            }
        }
    }

    /**
     * 方法二：迭代
     * 上面的算法也可以使用迭代（宽度优先搜索）的方法实现。维护一个队列，
     * 存储节点以及根到该节点的路径。一开始这个队列里只有根节点。在每一步迭代中，
     * 取出队列中的首节点，如果它是一个叶子节点，则将它对应的路径加入到答案中。
     * 如果它不是一个叶子节点，则将它的所有孩子节点加入到队列的末尾。
     * 当队列为空时，迭代结束。
     *
     * 复杂度分析
     * 时间复杂度：O(N)，每个节点只会被访问一次。
     * 空间复杂度：O(N)，在最坏情况下，队列中有 N 个节点。
     */
    public static List<String> solution2(TreeNode root) {
        LinkedList<String> paths = new LinkedList<>();
        if (null == root) {
            return paths;
        }
        LinkedList<TreeNode> nodeStack = new LinkedList();
        LinkedList<String> pathStack = new LinkedList();
        nodeStack.add(root);
        pathStack.add(Integer.toString(root.val));
        TreeNode node;
        String path;
        while (!nodeStack.isEmpty()) {
            node = nodeStack.pollLast();
            path = pathStack.pollLast();
            if (null == node.left && null == node.right) {
                paths.add(path);
            }
            if (null != node.left) {
                nodeStack.add(node.left);
                pathStack.add(path + "->" + node.left.val);
            }
            if (null != node.right) {
                nodeStack.add(node.right);
                pathStack.add(path + "->" + node.right.val);
            }
        }
        return paths;
    }

    public static void main(String[] args) {
        BinaryTreePaths.TreeNode root = new BinaryTreePaths.TreeNode(1);
        root.left = new BinaryTreePaths.TreeNode(2);
        root.right = new BinaryTreePaths.TreeNode(3);
        root.left.right = new BinaryTreePaths.TreeNode(5);
        LeetCodeUtil.logln("solution1() = " + BinaryTreePaths.solution1(root));
        LeetCodeUtil.logln("solution2() = " + BinaryTreePaths.solution2(root));
    }
}
