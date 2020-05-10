package com.wangcheng.leetcode.LeetCode.BinaryTree;

import com.sunfusheng.algo.common.model.TreeNode;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 【题目】
 * 236.二叉树的最近公共祖先
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * <p>
 * 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
 * 【示例】
 * ------- 3
 * ------ / \
 * ---- /    \
 * --- 5      1
 * -- / \    / \
 * - 6   2  0   8
 * ---- / \
 * --- 7   4
 * <p>
 * 示例 1:
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出: 3
 * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
 * <p>
 * 示例 2:
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出: 5
 * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
 * <p>
 * 说明:
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉树中。
 *
 * @author sunfusheng
 * @since 2020/5/10
 */
public class LowestCommonAncestorII {

    /**
     * 方法一：递归
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }

    /**
     * 方法一：存储父节点
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public static TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        HashMap<TreeNode, TreeNode> parentMap = new HashMap<>();
        parentMap.put(root, null);
        buildParentMap(root, parentMap);
        return findCommonAncestor(parentMap, p, q);
    }

    private static void buildParentMap(TreeNode root, HashMap<TreeNode, TreeNode> parentMap) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            parentMap.put(root.left, root);
        }
        if (root.right != null) {
            parentMap.put(root.right, root);
        }
        buildParentMap(root.left, parentMap);
        buildParentMap(root.right, parentMap);
    }

    private static TreeNode findCommonAncestor(HashMap<TreeNode, TreeNode> parentMap,
                                               TreeNode p, TreeNode q) {
        HashSet<TreeNode> path = new HashSet<>();
        while (parentMap.containsKey(p)) {
            path.add(p);
            p = parentMap.get(p);
        }
        while (!path.contains(q)) {
            q = parentMap.get(q);
        }
        return q;
    }

    public static void main(String[] args) {
        TreeNode node0 = new TreeNode(0);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);

        TreeNode root = node3;
        root.left = node5;
        root.right = node1;
        node5.left = node6;
        node5.right = node2;
        node1.left = node0;
        node1.right = node8;
        node2.left = node7;
        node2.right = node4;

        TreeNode node = lowestCommonAncestor(root, node5, node1);
        System.out.println("方法一输出1：" + node.value);
        node = lowestCommonAncestor(root, node5, node4);
        System.out.println("方法一输出2：" + node.value);

        node = lowestCommonAncestor2(root, node5, node1);
        System.out.println("方法二输出1：" + node.value);
        node = lowestCommonAncestor2(root, node5, node4);
        System.out.println("方法二输出2：" + node.value);
    }
}
