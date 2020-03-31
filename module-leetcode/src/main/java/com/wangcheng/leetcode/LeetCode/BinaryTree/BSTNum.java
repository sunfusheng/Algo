package com.wangcheng.leetcode.LeetCode.BinaryTree;

/**
 * 【题目】
 * 96.不同的二叉搜索树
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 * <p>
 * 示例:
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 * <p>
 * - 1         3     3      2      1
 * -- \       /     /      / \      \
 * --- 3     2     1      1   3      2
 * -- /     /       \                 \
 * - 2     1         2                 3
 *
 * @author sunfusheng
 * @since 2020/3/31
 */
public class BSTNum {

    /**
     * 动态规划求解方式
     *
     * @param n
     * @return
     */
    public static int numTrees(int n) {
        int[] G = new int[n + 1];
        G[0] = 1;
        G[1] = 1;

        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j <= i; ++j) {
                G[i] += G[j - 1] * G[i - j];
            }
        }
        return G[n];
    }
}
