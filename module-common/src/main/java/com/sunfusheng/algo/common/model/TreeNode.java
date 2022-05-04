package com.sunfusheng.algo.common.model;

import org.jetbrains.annotations.NotNull;

/**
 * @author sunfusheng
 * @since 2020/3/7
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @NotNull
    @Override
    public String toString() {
        return "{" + "val=" + val + ", left=" + left + ", right=" + right + '}';
    }
}