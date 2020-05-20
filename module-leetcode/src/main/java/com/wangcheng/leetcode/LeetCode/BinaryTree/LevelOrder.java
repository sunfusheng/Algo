package com.wangcheng.leetcode.LeetCode.BinaryTree;

import com.sunfusheng.algo.common.model.TreeNode;
import com.sunfusheng.algo.common.util.AlgoUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 【题目】
 * 102.二叉树的层序遍历
 * 给你一个二叉树，请你返回其按层序遍历得到的节点值。（即逐层地，从左到右访问所有节点）。
 * <p>
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 * --- 3
 * -- / \
 * - 9  20
 * --- /  \
 * -- 15   7
 * <p>
 * 返回其层次遍历结果：
 * [
 * - [3],
 * - [9,20],
 * - [15,7]
 * ]
 *
 * @author sunfusheng
 * @since 2020/4/23
 */
public class LevelOrder {

    /**
     * 解法一：广度优先遍历
     * <p>
     * 复杂度分析
     * 时间复杂度：O(N)，因为每个节点恰好会被运算一次。
     * 空间复杂度：O(N)，保存输出结果的数组包含 N 个节点的值。
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrder1(TreeNode root) {
        if (root == null) {
            return null;
        }

        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                list.add(node.value);

                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            res.add(list);
        }
        return res;
    }

    /**
     * 解法二：深度优先遍历
     * <p>
     * 复杂度分析
     * 时间复杂度：O(N)，因为每个节点恰好会被运算一次。
     * 空间复杂度：O(N)，保存输出结果的数组包含 N 个节点的值。
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrder2(TreeNode root) {
        if (root == null) {
            return null;
        }

        List<List<Integer>> res = new ArrayList<>();
        process(root, res, 0);
        return res;
    }

    private static void process(TreeNode root, List<List<Integer>> lists, int level) {
        if (root == null) {
            return;
        }

        if (level + 1 > lists.size()) {
            lists.add(new ArrayList<>());
        }
        List<Integer> list = lists.get(level);
        list.add(root.value);

        process(root.left, lists, level + 1);
        process(root.right, lists, level + 1);
    }

    public static void main(String[] args) {
        // 反序列化创建题目的二叉树
        String serializeStr = "3,9,20,null,null,15,7";
        TreeNode root = BinaryTreeUtil.deserializeByLevel(serializeStr);
        System.out.println("输入：" + serializeStr);

        System.out.println("解法一输出：");
        AlgoUtil.printList2(levelOrder1(root));

        System.out.println("解法二输出：");
        AlgoUtil.printList2(levelOrder2(root));
    }
}
