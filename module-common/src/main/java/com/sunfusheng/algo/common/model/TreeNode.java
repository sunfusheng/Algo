package com.sunfusheng.algo.common.model;

import org.jetbrains.annotations.NotNull;

/**
 * @author sunfusheng
 * @since 2020/3/7
 */
public class TreeNode {
    public int value;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int value) {
        this.value = value;
    }

    @NotNull
    @Override
    public String toString() {
        return "{" +
                "value=" + value +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}