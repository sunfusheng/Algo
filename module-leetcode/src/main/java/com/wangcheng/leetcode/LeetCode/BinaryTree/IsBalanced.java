package com.wangcheng.leetcode.LeetCode.BinaryTree;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 *【题目】
 * 110.平衡二叉树
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * <p>
 * 本题中，一棵高度平衡二叉树定义为：
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 * <p>
 *【示例】
 * 示例 1:
 * 给定二叉树 [3,9,20,null,null,15,7]
 * --- 3
 * -- / \
 * - 9  20
 * --- /  \
 * -- 15   7
 * 返回 true 。
 *
 * 示例 2:
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 * ------ 1
 * ----- / \
 * ---- 2   2
 * --- / \
 * -- 3   3
 * - / \
 *  4   4
 * 返回 false 。
 * 
 * @author liwangcheng
 * @date 2020/3/31.
 */
public class IsBalanced {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    static class TreeInfo {
        public final int height;
        public final boolean balanced;

        public TreeInfo(int height, boolean balanced) {
            this.height = height;
            this.balanced = balanced;
        }
    }

    /**
     * 方法一：自顶向下的递归
     * 定义方法 height，用于计算任意一个节点 p ∈ T 的高度
     * 接下来就是比较每个节点左右子树的高度。在一棵以 r 为根节点的树
     * T 中，只有每个节点左右子树高度差不大于 1 时，该树才是平衡的。
     * 因此可以比较每个节点左右两棵子树的高度差，然后向上递归。
     *
     * 复杂度分析
     * 时间复杂度：O(nlogn)。
     * 空间复杂度：O(n)。如果树完全倾斜，递归栈可能包含所有节点。
     */
    public static boolean solution1(TreeNode root) {
        if (null == root) {
            return true;
        }
        return Math.abs(height(root.left) - height(root.right)) < 2
                && solution1(root.left)
                && solution1(root.right);
    }

    private static int height(TreeNode root) {
        if (null == root) {
            return -1;
        }
        return 1 + Math.max(height(root.left), height(root.right));
    }

    /**
     * 方法二：自底向上的递归
     *【思路】
     * 方法一计算 height 存在大量冗余。每次调用 height 时，要同时计算其子树高度。
     * 但是自底向上计算，每个子树的高度只会计算一次。可以递归先计算当前节点的子节点高度，
     * 然后再通过子节点高度判断当前节点是否平衡，从而消除冗余。
     *【算法】
     * 使用与方法一中定义的 height 方法。自底向上与自顶向下的逻辑相反，
     * 首先判断子树是否平衡，然后比较子树高度判断父节点是否平衡。
     * 算法如下：
     * 检查子树是否平衡。如果平衡，则使用它们的高度判断父节点是否平衡，并计算父节点的高度。
     *
     * 复杂度分析
     * 时间复杂度：O(n)，计算每棵子树的高度和判断平衡操作都在恒定时间内完成。
     * 空间复杂度：O(n)，如果树不平衡，递归栈可能达到 O(n)。
     */
    public static boolean solution2(TreeNode root) {
        return isBalanceTreeHelper(root).balanced;
    }

    private static TreeInfo isBalanceTreeHelper(TreeNode root) {
        if (null == root) {
            return new TreeInfo(-1, true);
        }
        TreeInfo left = isBalanceTreeHelper(root.left);
        if (!left.balanced) {
            return new TreeInfo(-1, false);
        }
        TreeInfo right = isBalanceTreeHelper(root.right);
        if (!right.balanced) {
            return new TreeInfo(-1, false);
        }
        if (Math.abs(left.height - right.height) < 2) {
            return new TreeInfo(Math.max(left.height, right.height) + 1, true);
        }
        return new TreeInfo(-1, false);
    }

    public static void main(String[] args) {
        IsBalanced.TreeNode p = new IsBalanced.TreeNode(1);
        p.left = new IsBalanced.TreeNode(2);
        p.left.left = new IsBalanced.TreeNode(3);
        p.left.left.left = new IsBalanced.TreeNode(4);
        p.right = new IsBalanced.TreeNode(2);
        p.right.right = new IsBalanced.TreeNode(4);
        p.right.right.right = new IsBalanced.TreeNode(3);
        IsBalanced.TreeNode q = new IsBalanced.TreeNode(1);
        q.left = new IsBalanced.TreeNode(2);
        q.left.left = new IsBalanced.TreeNode(3);
        q.right = new IsBalanced.TreeNode(2);
        q.right.left = new IsBalanced.TreeNode(3);
        LeetCodeUtil.logln("solution1 = " + IsBalanced.solution1(p));
        LeetCodeUtil.logln("solution1 = " + IsBalanced.solution1(q));
        LeetCodeUtil.logln("solution2 = " + IsBalanced.solution2(p));
        LeetCodeUtil.logln("solution2 = " + IsBalanced.solution2(q));
    }
}
