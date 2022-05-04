package com.wangcheng.leetcode.LeetCode.BinaryTree;

import com.sunfusheng.algo.common.model.TreeNode;
import com.sunfusheng.algo.common.util.AlgoUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 【题目】
 * 103.二叉树的锯齿形层次遍历
 * 给定一个二叉树，返回其节点值的锯齿形层次遍历。
 * （即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
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
 * - [3],
 * - [20,9],
 * - [15,7]
 * ]
 *
 * @author sunfusheng
 * @since 2020/5/7
 */
public class ZigzagLevelOrder {

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
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return null;
        }

        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int height = 1; // 奇数从左至右，偶数从右至左
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (height % 2 == 0) {
                    list.add(0, node.val);
                } else {
                    list.add(node.val);
                }

                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            height++;
            res.add(list);
        }
        return res;
    }

    public static void main(String[] args) {
        String serializeStr = "3,9,20,null,null,15,7";
        TreeNode root = BinaryTreeUtil.deserializeByLevel(serializeStr);
        System.out.println("输入：" + serializeStr);

        System.out.println("输出：");
        AlgoUtil.printList2(zigzagLevelOrder(root));
    }
}
