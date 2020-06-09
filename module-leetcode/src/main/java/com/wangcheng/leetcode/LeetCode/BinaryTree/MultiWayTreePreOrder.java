package com.wangcheng.leetcode.LeetCode.BinaryTree;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *【题目】
 * 589.N叉树的前序遍历
 * 给定一个 N 叉树，返回其节点值的前序遍历。
 *
 * 例如，给定一个3叉树 :
 *
 * ------ 1
 * ---- / \ \
 * --- 3  2 4
 * -- / \
 * - 5  6
 *
 * 返回其前序遍历: [1,3,5,6,2,4]。
 *【说明】
 * 递归法很简单，你可以使用迭代法完成此题吗?
 *
 * @author liwangcheng
 * @date 2020/6/5.
 */
public class MultiWayTreePreOrder {

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

    /**
     * 方法一：递归
     */
    private static List<Integer> res = new ArrayList<Integer>();
    public static List<Integer> solution1(Node root) {
        helper(root);
        return res;
    }

    private static void helper(Node root) {
        if (null == root) {
            return;
        }
        res.add(root.val);
        if (LeetCodeUtil.isEmpty(root.children)) {
            return;
        }
        for (Node node : root.children) {
            helper(node);
        }
    }

    /**
     * 方法二：迭代
     */
    public static List<Integer> solution2(Node root) {
        if (null == root) {
            return Collections.emptyList();
        }
        List<Integer> res = new ArrayList<>();
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            root = queue.pollLast();
            res.add(root.val);
            if (LeetCodeUtil.isEmpty(root.children)) {
                continue;
            }
            for (int i = root.children.size() - 1; i >= 0; i--) {
                queue.add(root.children.get(i));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        MultiWayTreePreOrder.Node root = new MultiWayTreePreOrder.Node(1);
        root.children = new ArrayList<>();
        MultiWayTreePreOrder.Node left = new MultiWayTreePreOrder.Node(3);
        root.children.add(left);
        root.children.add(new MultiWayTreePreOrder.Node(2));
        root.children.add(new MultiWayTreePreOrder.Node(4));
        left.children = new ArrayList<>();
        left.children.add(new MultiWayTreePreOrder.Node(5));
        left.children.add(new MultiWayTreePreOrder.Node(6));
        LeetCodeUtil.logln("solution1() = " + MultiWayTreePreOrder.solution1(root));
        LeetCodeUtil.logln("solution2() = " + MultiWayTreePreOrder.solution2(root));
    }
}
