package com.wangcheng.leetcode.LeetCode.BinaryTree;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 *【题目】
 * 235.二叉搜索树的最近公共祖先
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 * <p>
 * 百度百科中最近公共祖先的定义为：
 * “对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，
 * 满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * <p>
 *【示例】
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 *
 * -------- 6
 * ------ /   \
 * ----- 2     8
 * ---- / \   / \
 * --- 0  4  7  9
 * ----- / \
 * ---- 3  5
 *
 * 输出: 6
 * 解释: 节点 2 和节点 8 的最近公共祖先是 6。
 *【说明】
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉搜索树中。
 *
 * @author liwangcheng
 * @date 2020/4/27.
 */
public class LowestCommonAncestor {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 方法一 （递归）
     * 思路
     * 节点 p，q 的最近公共祖先（LCA）是距离这两个节点最近的公共祖先节点。
     * 在这里最近考虑的是节点的深度。
     * 算法
     * - 从根节点开始遍历树
     * - 如果节点 p 和节点 q 都在右子树上，那么以右孩子为根节点继续 1 的操作
     * - 如果节点 p 和节点 q 都在左子树上，那么以左孩子为根节点继续 1 的操作
     * - 如果条件 2 和条件 3 都不成立，这就意味着我们已经找到节 p 和节点 q 的 LCA 了
     * <p>
     * 复杂度分析
     * 时间复杂度：O(N)
     * 其中 N 为 BST 中节点的个数，在最坏的情况下我们可能需要访问 BST 中所有的节点。
     * 空间复杂度：O(N)
     * 所需开辟的额外空间主要是递归栈产生的，之所以是 N 是因为 BST 的高度为 N。
     */
    public static TreeNode solution1(TreeNode root, TreeNode p, TreeNode q) {
        int parentVal = root.val;
        int pVal = p.val;
        int qVal = q.val;
        if (pVal > parentVal && qVal > parentVal) {
            return solution1(root.right, p, q);
        } else if (pVal < parentVal && qVal < parentVal) {
            return solution1(root.left, p, q);
        } else {
            return root;
        }
    }

    /**
     * 方法二 （迭代）
     * 算法：
     * 实际上这个问题本身就是可以迭代的，只需要找到分割点就可以了。
     * 这个分割点就是能让节点 p 和节点 q 不能在同一颗子树上的那个节点，
     * 或者是节点 p 和节点 q 中的一个，这种情况下其中一个节点是
     * 另一个节点的父亲节点。
     *
     * 复杂度分析
     * 时间复杂度：O(N)
     *  其中 N 为 BST 中节点的个数，在最坏的情况下可能需要遍历 BST 中所有的节点。
     * 空间复杂度：O(1)
     */
    public static TreeNode solution2(TreeNode root, TreeNode p, TreeNode q) {
        int pVal = p.val;
        int qVal = q.val;
        TreeNode node = root;
        while (null != node) {
            int parentVal = node.val;
            if (pVal > parentVal && qVal > parentVal) {
                node = node.right;
            } else if (pVal < parentVal && qVal < parentVal) {
                node = node.left;
            } else {
                return node;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        LowestCommonAncestor.TreeNode root = new LowestCommonAncestor.TreeNode(6);
        root.left = new LowestCommonAncestor.TreeNode(2);
        root.right = new LowestCommonAncestor.TreeNode(8);
        root.left.left = new LowestCommonAncestor.TreeNode(0);
        root.left.right = new LowestCommonAncestor.TreeNode(4);
        root.right.left = new LowestCommonAncestor.TreeNode(7);
        root.right.right = new LowestCommonAncestor.TreeNode(9);
        root.left.right.left = new LowestCommonAncestor.TreeNode(3);
        root.left.right.right = new LowestCommonAncestor.TreeNode(5);
        LeetCodeUtil.logln("solution1()" + LowestCommonAncestor.solution1(root, root.left.left, root.left.right.right));
        LeetCodeUtil.logln("solution2()" + LowestCommonAncestor.solution2(root, root.left.left, root.left.right.right));
    }
}
