package com.wangcheng.leetcode.LeetCode.BinaryTree;

import com.sunfusheng.algo.common.model.TreeNode;
import com.sunfusheng.algo.common.util.AlgoUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 【题目】
 * 199.二叉树的右视图
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 * <p>
 * 示例:
 * 输入: [1,2,3,null,4]
 * 输出: [1, 3, 4]
 * <p>
 * 解释:
 * ---- 1            <---
 * -- /  \
 * - 2    3          <---
 * -- \
 * --- 4             <---
 *
 * @author sunfusheng
 * @since 2020/5/20
 */
public class RightSideView {

    /**
     * 解法一：广度优先遍历
     * <p>
     * 时间复杂度：O(N)
     * 空间复杂度：O(N)
     *
     * @param root
     * @return
     */
    public static List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                if (i == size - 1) {
                    res.add(node.val);
                }
            }
        }
        return res;
    }

    /**
     * 解法二：深度优先遍历
     * <p>
     * 时间复杂度：O(N)
     * 空间复杂度：O(N)
     *
     * @param root
     * @return
     */
    public static List<Integer> rightSideView2(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<Integer> res = new ArrayList<>();
        dfs(root, res, 0);
        return res;
    }

    private static void dfs(TreeNode root, List<Integer> list, int depth) {
        if (root == null) {
            return;
        }
        if (depth == list.size()) {
            list.add(root.val);
        }
        dfs(root.right, list, depth + 1);
        dfs(root.left, list, depth + 1);
    }

    public static void main(String[] args) {
        String serializeStr = "1,2,3,null,4";
        TreeNode root = BinaryTreeUtil.deserializeByLevel(serializeStr);
        System.out.println("输入：" + serializeStr);
        System.out.print("输出：");
        AlgoUtil.printlnList(rightSideView(root));
    }
}
