package com.wangcheng.leetcode.LeetCode.BinaryTree;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *【题目】
 * 559.N叉树的最大深度
 * 给定一个 N 叉树，找到其最大深度。
 * <p>
 * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
 * <p>
 * 例如，给定一个 3叉树 :
 *
 * ------ 1
 * ---- / \ \
 * --- 3  2 4
 * -- / \
 * - 5  6
 *
 * 应返回其最大深度，3。
 *【说明】
 * 树的深度不会超过 1000。
 * 树的节点总不会超过 5000。
 *
 * @author liwangcheng
 * @date 2020/5/27.
 */
public class MultiMaxDepth {

    public static class Node {

        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    private static class Pair<F, S> {
        F first;
        S second;

        public Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }
    }

    /**
     * 方法一: 递归
     * 算法
     * 解决这个问题的最直观方法就是递归。
     * 此处展示了深度优先搜索的策略。
     *
     * 复杂度分析
     * 时间复杂度：每个节点遍历一次，所以时间复杂度是 O(N)，
     *  其中 N 为节点数。
     * 空间复杂度：最坏情况下, 树完全非平衡，
     *  例如 每个节点有且仅有一个孩子节点，递归调用会发生 N
     *  次（等于树的深度），所以存储调用栈需要 O(N)。
     *  但是在最好情况下（树完全平衡），树的高度为 log(N)。
     *  所以在此情况下空间复杂度为 O(log(N))。
     */
    public static int solution1(Node root) {
        return maxDepth(root);
    }

    private static int maxDepth(Node root) {
        if (null == root) {
            return 0;
        } else if (null == root.children || root.children.isEmpty()) {
            return 1;
        } else {
            List<Integer> list = new ArrayList<>();
            for (Node item : root.children) {
                list.add(maxDepth(item));
            }
            return Collections.max(list) + 1;
        }
    }

    /**
     * 方法二: 迭代
     * 可以在堆栈的帮助下将上面的递归转换为迭代。
     * 思路
     * 使用深度优先搜索策略访问每个节点，同时更新每次访问时的最大深度。
     *
     * 所以可以从包含根节点的、对应深度为 1 的栈开始。
     * 然后继续迭代，从栈中弹出当前节点并将子节点压入栈中，每次都更新对应深度。
     *
     * 复杂度分析
     * 时间复杂度：O(N)。
     * 空间复杂度：O(N)。
     */
    public static int solution2(Node root) {
        if (null == root) {
            return 0;
        } else if (null == root.children || root.children.isEmpty()) {
            return 1;
        }
        Queue<Pair<Node, Integer>> stack = new LinkedList<>();
        stack.add(new Pair<>(root, 1));
        int depth = 1;
        while (!stack.isEmpty()) {
            Pair<Node, Integer> current = stack.poll();
            root = current.first;
            int curDepth = current.second;
            if (null != root) {
                depth = Math.max(depth, curDepth);
                if (null == root.children || root.children.isEmpty()) {
                    continue;
                }
                for (Node d : root.children) {
                    stack.add(new Pair<>(d, curDepth + 1));
                }
            }
        }
        return depth;
    }

    public static void main(String[] args) {
        MultiMaxDepth.Node root = new MultiMaxDepth.Node(1);
        root.children = new ArrayList<>();
        root.children.add(new MultiMaxDepth.Node(3));
        root.children.add(new MultiMaxDepth.Node(2));
        root.children.add(new MultiMaxDepth.Node(4));
        root.children.get(0).children = new ArrayList<>();
        root.children.get(0).children.add(new MultiMaxDepth.Node(5));
        root.children.get(0).children.add(new MultiMaxDepth.Node(6));

        LeetCodeUtil.logln("solution1() = " + MultiMaxDepth.solution1(root));
        LeetCodeUtil.logln("solution2() = " + MultiMaxDepth.solution2(root));
    }
}
