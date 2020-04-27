package com.wangcheng.leetcode.LeetCode.BinaryTree;

import com.sunfusheng.algo.common.model.TreeNode;
import com.sunfusheng.algo.common.util.AlgoUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 【题目】
 * 107.二叉树的层次遍历 II
 * 给定一个二叉树，返回其节点值自底向上的层次遍历。
 * （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 * <p>
 * 【例如】
 * 给定二叉树 [3,9,20,null,null,15,7],
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

    /**
     * 解法一：广度优先遍历
     * <p>
     * 复杂度分析
     * 时间复杂度：O(N)
     * 空间复杂度：O(N)
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> solution1(TreeNode root) {
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
                list.add(node.value);
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
        // 反序列化创建题目的二叉树
        String serializeStr = "3,9,20,null,null,15,7";
        TreeNode root = BinaryTreeUtil.deserializeByLevel(serializeStr);
        System.out.println("输入：" + serializeStr);

        System.out.println("输出：");
        AlgoUtil.printList2(solution1(root));
    }
}
