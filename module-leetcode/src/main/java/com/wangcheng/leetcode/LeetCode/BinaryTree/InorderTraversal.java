package com.wangcheng.leetcode.LeetCode.BinaryTree;

import com.sunfusheng.algo.common.model.TreeNode;
import com.sunfusheng.algo.common.util.AlgoUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 【题目】
 * 94.二叉树的中序遍历
 * 给定一个二叉树，返回它的中序遍历。
 * <p>
 * 示例:
 * 输入: [1,null,2,3,null]
 * - 1
 * -- \
 * --- 2
 * -- /
 * - 3
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 * @author sunfusheng
 * @since 2020/3/26
 */
public class InorderTraversal {

    /**
     * 方法一：递归
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<Integer> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }

    private static void dfs(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        dfs(node.left, list);
        list.add(node.value);
        dfs(node.right, list);
    }

    /**
     * 方法二：基于栈的遍历
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public static List<Integer> inorderTraversal2(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            res.add(cur.value);
            cur = cur.right;
        }
        return res;
    }

    public static void main(String[] args) {
        String serializeStr = "1,null,2,3,null";
        TreeNode root = BinaryTreeUtil.deserializeByLevel(serializeStr);
        System.out.println("输入：" + serializeStr);

        System.out.print("方法一输出：");
        AlgoUtil.printlnList(inorderTraversal(root));
        System.out.print("方法二输出：");
        AlgoUtil.printlnList(inorderTraversal2(root));
    }
}
