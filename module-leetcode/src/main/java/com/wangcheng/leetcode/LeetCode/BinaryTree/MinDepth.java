package com.wangcheng.leetcode.LeetCode.BinaryTree;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.LinkedList;

/**
 *【题目】
 * 111.二叉树的最小深度
 * 给定一个二叉树，找出其最小深度。
 * <p>
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 *【示例】
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 * --- 3
 * -- / \
 * - 9  20
 * --- /  \
 * -- 15   7
 * 返回它的最小深度 2.
 *
 * @author liwangcheng
 * @date 2020/4/1.
 */
public class MinDepth {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
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
     * 方法 1：递归
     * 用深度优先搜索来解决这个问题
     *
     * 复杂度分析
     *
     * 时间复杂度：我们访问每个节点一次，时间复杂度为 O(N) ，其中 N 是节点个数。
     * 空间复杂度：最坏情况下，整棵树是非平衡的，
     * 例如每个节点都只有一个孩子，递归会调用 N（树的高度）次，因此栈的空间开销是 O(N)。
     * 但在最好情况下，树是完全平衡的，高度只有 log(N)，
     * 因此在这种情况下空间复杂度只有 O(log(N)) 。
     */
    public static int solution1(TreeNode root) {
        if (null == root) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int minDepth = Integer.MAX_VALUE;
        if (null != root.left) {
            minDepth = Math.min(minDepth, solution1(root.left));
        }
        if (null != root.right) {
            minDepth = Math.min(minDepth, solution1(root.right));
        }
        return minDepth + 1;
    }

    /**
     * 方法 2：深度优先搜索迭代
     * 可以利用栈将上述解法中的递归变成迭代
     * 想法是对于每个节点，按照深度优先搜索的策略访问，同时在访问到叶子节点时更新最小深度。
     * 从一个包含根节点的栈开始，当前深度为 1。
     * 然后开始迭代：弹出当前栈顶元素，将它的孩子节点压入栈中。当遇到叶子节点时更新最小深度。
     *
     * 复杂度分析
     * 时间复杂度：每个节点恰好被访问一遍，复杂度为 O(N)。
     * 空间复杂度：最坏情况下我们会在栈中保存整棵树，此时空间复杂度为 O(N)。
     */
    public static int solution2(TreeNode root) {
        LinkedList<Pair<TreeNode, Integer>> stack = new LinkedList<>();
        if (null == root) {
            return 0;
        } else {
            stack.add(new Pair<>(root, 1));
        }
        int minDepth = Integer.MAX_VALUE;
        while (!stack.isEmpty()) {
            // 检索并删除此列表的最后一个元素
            Pair<TreeNode, Integer> current = stack.pollLast();
            root = current.first;
            int currentDepth = current.second;
            if (null == root.left && null == root.right) {
                minDepth = Math.min(minDepth, currentDepth);
            }
            if (null != root.left) {
                stack.add(new Pair<>(root.left, currentDepth + 1));
            }
            if (null != root.right) {
                stack.add(new Pair<>(root.right, currentDepth + 1));
            }
        }
        return minDepth;
    }

    /**
     * 方法 3：宽度优先搜索迭代
     * 深度优先搜索方法的缺陷是所有节点都必须访问到，以保证能够找到最小深度。因此复杂度是 O(N)。
     * 一个优化的方法是利用宽度优先搜索，按照树的层次去迭代，
     * 第一个访问到的叶子就是最小深度的节点，这样就不要遍历所有的节点了。
     *
     * 复杂度分析
     * 时间复杂度：最坏情况下，这是一棵平衡树，需要按照树的层次一层一层的访问完所有节点，
     * 除去最后一层的节点。这样访问了 N/2 个节点，因此复杂度是 O(N)。
     * 空间复杂度：和时间复杂度相同，也是 O(N)。
     */
    public static int solution3(TreeNode root) {
        LinkedList<Pair<TreeNode, Integer>> stack = new LinkedList<>();
        if (null == root) {
            return 0;
        } else {
            stack.add(new Pair<>(root, 1));
        }
        int currentDepth = 0;
        while (!stack.isEmpty()) {
            // 检索并删除此列表的头部（第一个元素）
            Pair<TreeNode, Integer> current = stack.poll();
            root = current.first;
            currentDepth = current.second;
            if (null == root.left && null == root.right) {
                break;
            }
            if (null != root.left) {
                stack.add(new Pair<>(root.left, currentDepth + 1));
            }
            if (null != root.right) {
                stack.add(new Pair<>(root.right, currentDepth + 1));
            }
        }
        return currentDepth;
    }

    public static void main(String[] args) {
        MinDepth.TreeNode p = new MinDepth.TreeNode(1);
        p.left = new MinDepth.TreeNode(2);
        p.right = new MinDepth.TreeNode(2);
        p.right.left = new MinDepth.TreeNode(4);
        p.right.right = new MinDepth.TreeNode(3);
        p.right.right.left = new MinDepth.TreeNode(3);
        MinDepth.TreeNode q = new MinDepth.TreeNode(1);
        q.left = new MinDepth.TreeNode(2);
        q.left.left = new MinDepth.TreeNode(3);
        q.right = new MinDepth.TreeNode(2);
        q.right.left = new MinDepth.TreeNode(3);
        MinDepth.TreeNode p2 = p;
        MinDepth.TreeNode q2 = q;
        MinDepth.TreeNode p3 = p;
        MinDepth.TreeNode q3 = q;
        LeetCodeUtil.logln("solution1(p) = " + MinDepth.solution1(p));
        LeetCodeUtil.logln("solution1(q) = " + MinDepth.solution1(q));
        LeetCodeUtil.logln("solution2(p2) = " + MinDepth.solution2(p2));
        LeetCodeUtil.logln("solution2(q2) = " + MinDepth.solution2(q2));
        LeetCodeUtil.logln("solution3(p3) = " + MinDepth.solution3(p3));
        LeetCodeUtil.logln("solution3(q3) = " + MinDepth.solution3(q3));
    }
}
