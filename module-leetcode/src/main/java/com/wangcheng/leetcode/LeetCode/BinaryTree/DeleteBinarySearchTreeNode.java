package com.wangcheng.leetcode.LeetCode.BinaryTree;

import com.sunfusheng.algo.common.model.TreeNode;

/**
 * 【题目】
 * 450.删除二叉搜索树中的节点
 * <p>
 * 二叉搜索树（Binary Search Tree），（又：二叉查找树，二叉排序树）它或者是一棵空树，
 * 或者是具有下列性质的二叉树： 若它的左子树不空，则左子树上所有结点的值均小于它的根结点的值；
 * 若它的右子树不空，则右子树上所有结点的值均大于它的根结点的值； 它的左、右子树也分别为二叉搜索树。
 * <p>
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，
 * 并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
 * <p>
 * 一般来说，删除节点可分为两个步骤：
 * 1、首先找到需要删除的节点；
 * 2、如果找到了，删除它。
 * 说明：要求算法时间复杂度为 O(h)，h 为树的高度。
 * <p>
 * 示例:
 * root = [5,3,6,2,4,null,7]
 * key = 3
 * ----- 5
 * ---- / \
 * --- 3   6
 * -- / \   \
 * - 2   4   7
 * <p>
 * 给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
 * <p>
 * 一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
 * ----- 5
 * ---- / \
 * --- 4   6
 * -- /     \
 * - 2       7
 * <p>
 * 另一个正确答案是 [5,2,6,null,4,null,7]。
 * --- 5
 * -- / \
 * - 2   6
 * -- \   \
 * --- 4   7
 *
 * @author sunfusheng
 * @since 2020/3/25
 */
public class DeleteBinarySearchTreeNode {

    /**
     * 方法一
     * <p>
     * 时间复杂度：O(logN)
     * 空间复杂度：O(h)
     *
     * @param root
     * @param key
     * @return
     */
    public static TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (root.value > key) {
            root.left = deleteNode(root.left, key);
        } else if (root.value < key) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            TreeNode rightSmallest = root.right;
            while (rightSmallest.left != null) {
                rightSmallest = rightSmallest.left;
            }
            rightSmallest.left = root.left;
            return root.right;
        }
        return root;
    }

    public static void main(String[] args) {
        // 层遍历反序列化创建题目的二叉树
        String serializeStr = "5,3,6,2,4,null,7";
        TreeNode root = BinaryTreeUtil.deserializeByLevel(serializeStr);
        System.out.println("输入：" + serializeStr);

        // 删除二叉搜索树中的节点
        root = deleteNode(root, 3);
        System.out.println("输出：" + BinaryTreeUtil.serializeByLevel(root));
    }
}
