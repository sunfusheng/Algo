package com.wangcheng.leetcode.LeetCode.BinaryTree;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *【题目】
 * 590.N叉树的后序遍历
 * 给定一个 N 叉树，返回其节点值的后序遍历。
 *
 * 例如，给定一个 3叉树 :
 *
 * ------ 1
 * ---- / \ \
 * --- 3  2 4
 * -- / \
 * - 5  6
 *
 * 返回其后序遍历: [5,6,3,2,4,1].
 *【说明】
 * 递归法很简单，你可以使用迭代法完成此题吗?
 *
 * @author liwangcheng
 * @date 2020/6/9.
 */
public class MultiWayTreePostOrder {

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
        if (LeetCodeUtil.isEmpty(root.children)) {
            res.add(root.val);
            return;
        }
        for (Node node : root.children) {
            helper(node);
        }
        res.add(root.val);
    }

    /**
     * 方法二：迭代
     */
    public static List<Integer> solution2(Node root) {
        if (null == root) {
            return Collections.emptyList();
        }
        List<Integer> res = new ArrayList<>();
        LinkedList<Node> stack = new LinkedList<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            root = stack.pollLast();
            res.add(0, root.val);
            if (LeetCodeUtil.isEmpty(root.children)) {
                continue;
            }
            for (Node node : root.children) {
                stack.add(node);
            }
        }
        return res;
    }

}
