package com.wangcheng.leetcode.LeetCode.BinaryTree;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 * 【题目】
 * 108.将有序数组转换为二叉搜索树
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 * <p>
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 * <p>
 * 【示例】
 * <p>
 * 给定有序数组: [-10,-3,0,5,9],
 * 一个可能的答案是：[0,-3,9,-10,null,5]，
 * 它可以表示下面这个高度平衡二叉搜索树：
 * <p>
 * ----- 0
 * ---- / \
 * -- -3   9
 * -- /   /
 * - 10  5
 * 【提示】
 * 高度平衡意味着每次必须选择中间数字作为根节点。
 * 这对于奇数个数的数组是有用的，
 * 但对偶数个数的数组没有预定义的选择方案。
 * <p>
 * 对于偶数个数的数组，要么选择中间位置左边的元素作为根节点，
 * 要么选择中间位置右边的元素作为根节点，
 * 不同的选择方案会创建不同的平衡二叉搜索树。
 *
 * @author liwangcheng
 * @date 2020/3/31.
 */
public class SortedArrayToBST {


    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "{\"TreeNode\":{"
                    + "\"val\":\"" + val + "\""
                    + ", \"left\":" + left
                    + ", \"right\":" + right
                    + "}}";
        }
    }

    private static int[] nums;

    /**
     * 方法一：中序遍历：始终选择中间位置左边元素作为根节点
     * - 方法 helper(left, right) 使用数组 nums 中索引从 left 到 right 的元素创建 BST：
     * + 如果 left > right，子树中不存在元素，返回空。
     * + 找出中间元素：p = (left + right) / 2。
     * + 创建根节点：root = TreeNode(nums[p])。
     * + 递归创建左子树 root.left = helper(left, p - 1) 和右子树 root.right = helper(p + 1, right)。
     * - 返回 helper(0, len(nums) - 1)。
     * <p>
     * 复杂度分析
     * <p>
     * 时间复杂度：O(N)，每个元素只访问一次。
     * 空间复杂度：O(N)，二叉搜索树空间 O(N)，递归栈深度 O(logN)。
     */
    public static TreeNode solution1(int[] nums) {
        SortedArrayToBST.nums = nums;
        return helper1(0, nums.length - 1);
    }

    private static TreeNode helper1(int left, int right) {
        if (left > right) {
            return null;
        }
        int p = (left + right) / 2;
        TreeNode root = new TreeNode(nums[p]);
        root.left = helper1(left, p - 1);
        root.right = helper1(p + 1, right);
        return root;
    }

    /**
     * 方法二：中序遍历：始终选择中间位置右边元素作为根节点
     * - 方法 helper(left, right) 使用数组 nums 中索引从 left 到 right 的元素创建 BST：
     * + 如果 left > right，子树中不存在元素，返回空。
     * + 找出中间位置右边元素：
     * + p = (left + right) // 2。
     * + 如果 left + right 是偶数，则 p + 1。
     * + 创建根节点：root = TreeNode(nums[p])。
     * + 递归创建左子树 root.left = helper(left, p - 1) 和右子树 root.right = helper(p + 1, right)。
     * - 返回 helper(0, len(nums) - 1)。
     */
    public static TreeNode solution2(int[] nums) {
        SortedArrayToBST.nums = nums;
        return helper2(0, nums.length - 1);
    }

    private static TreeNode helper2(int left, int right) {
        if (left > right) {
            return null;
        }
        int p = (left + right) / 2;
        if ((left + right) % 2 == 1) {
            ++p;
        }
        TreeNode root = new TreeNode(nums[p]);
        root.left = helper2(left, p - 1);
        root.right = helper2(p + 1, right);
        return root;
    }

    public static void main(String[] args) {
        SortedArrayToBST.TreeNode root = SortedArrayToBST.solution1(new int[]{-10, -3, 0, 5, 9});
        LeetCodeUtil.logln("solution1([-10, -3, 0, 5, 9]) = " + root);
        root = SortedArrayToBST.solution2(new int[]{-10, -3, 0, 5, 9});
        LeetCodeUtil.logln("solution2([-10, -3, 0, 5, 9]) = " + root);
    }
}
