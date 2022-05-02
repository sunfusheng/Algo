package com.wangcheng.leetcode.LeetCode.BinaryTree;

import com.sunfusheng.algo.common.model.TreeNode;
import com.sunfusheng.algo.common.util.AlgoUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 【题目】
 * 145.二叉树的后序遍历
 * 给定一个二叉树，返回它的后序遍历。
 * <p>
 * 输入: [1,null,2,3,null]
 * - 1
 * -- \
 * --- 2
 * -- /
 * - 3
 * 输出: [1,2,3]
 * <p>
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 * @author sunfusheng
 * @since 2020/5/7
 */
public class PostorderTraversal {

    /**
     * 方法一：递归
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public static List<Integer> postorderTraversal(TreeNode root) {
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
        dfs(node.right, list);
        list.add(node.value);
    }

    /**
     * 方法二：基于栈的遍历
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public static List<Integer> postorderTraversal2(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode prev = null;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.peek();
            if (cur.right != null && cur.right != prev) {
                cur = cur.right;
            } else {
                stack.pop();
                res.add(cur.value);
                prev = cur;
                cur = null;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String serializeStr = "1,null,2,3,null";
        TreeNode root = BinaryTreeUtil.deserializeByLevel(serializeStr);
        System.out.println("输入：" + serializeStr);

        System.out.print("方法一输出：");
        AlgoUtil.printlnList(postorderTraversal(root));
        System.out.print("方法二输出：");
        AlgoUtil.printlnList(postorderTraversal2(root));
    }
}
