package com.wangcheng.leetcode.LeetCode.BinaryTree;

import com.sunfusheng.algo.common.model.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 【题目】
 * 94.二叉树的中序遍历
 * <p>
 * 给定一个二叉树，返回它的中序 遍历。
 * <p>
 * 示例:
 * 输入: [1,null,2,3]
 * --- 1
 * ---- \
 * ----- 2
 * ---- /
 * --- 3
 * <p>
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 * @author sunfusheng
 * @since 2020/3/26
 */
public class InorderTraversal {

    // 递归实现中序遍历
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorderTraversalRecur(root, res);
        return res;
    }

    private static void inorderTraversalRecur(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inorderTraversalRecur(root.left, list);
        list.add(root.value);
        inorderTraversalRecur(root.right, list);
    }

    // 非递归实现中序遍历
    public static List<Integer> inorderTraversalUnRecur(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                TreeNode node = stack.pop();
                res.add(node.value);
                root = node.right;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String serializeStr = "1 # 2 3 # # #";
        TreeNode root = BinaryTreeUtil.deserializeByLevel(serializeStr);
        System.out.println("输入：" + serializeStr);

        List<Integer> res = inorderTraversalUnRecur(root);
        System.out.print("输出：");
        for (int val : res) {
            System.out.print(val + " ");
        }
    }
}
