package com.wangcheng.leetcode.LeetCode.BinaryTree;

import com.sunfusheng.algo.common.model.TreeNode;
import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 * 【题目】
 * 572.另一个树的子树
 * 给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有
 * 相同结构和节点值的子树。
 * <p>
 * s 的一个子树包括 s 的一个节点和这个节点的所有子孙。
 * s 也可以看做它自身的一棵子树。
 * <p>
 * 【示例】
 * 示例 1:
 * 给定的树 s:
 * <p>
 * ----- 3
 * ---- / \
 * --- 4   5
 * -- / \
 * - 1   2
 * 给定的树 t：
 * <p>
 * --- 4
 * -- / \
 * - 1   2
 * 返回 true，因为 t 与 s 的一个子树拥有相同的结构和节点值。
 * <p>
 * 示例 2:
 * 给定的树 s：
 * <p>
 * ----- 3
 * ---- / \
 * --- 4   5
 * -- / \
 * - 1   2
 * ---- /
 * --- 0
 * 给定的树 t：
 * <p>
 * --- 4
 * -- / \
 * - 1   2
 * 返回 false。
 *
 * @author liwangcheng
 * @date 2020/6/2.
 */
public class IsSubtree {

    /**
     * 递归
     * 思路
     * 看到题目描述，首先判断一个树是否是另一棵树的子树，很明显
     * 想到可以用递归，但是两棵树完全相同也可以看做一棵树是另一
     * 棵树的子树。
     * 所以自然而然想到用一个判断两棵树是否相同的递归函数。
     */
    public static boolean solution(TreeNode s, TreeNode t) {
        if (null == t) {
            return true;
        }
        if (null == s) {
            return false;
        }
        return solution(s.left, t) || solution(s.right, t) || isSameTree(s, t);
    }

    private static boolean isSameTree(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }
        if (s.val != t.val) {
            return false;
        }
        return isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
    }

    public static void main(String[] args) {
        TreeNode s = new TreeNode(3);
        s.left = new TreeNode(4);
        s.right = new TreeNode(5);
        s.left.left = new TreeNode(1);
        s.left.right = new TreeNode(2);
        TreeNode t = new TreeNode(4);
        t.left = new TreeNode(1);
        t.right = new TreeNode(2);
        LeetCodeUtil.logln("solution() = " + solution(s, t));

        s.left.right = new TreeNode(2);
        s.left.right.left = new TreeNode(0);
        LeetCodeUtil.logln("solution() = " + solution(s, t));
    }
}
