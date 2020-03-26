package com.wangcheng.leetcode.LeetCode.BinaryTree;

import com.sunfusheng.algo.common.model.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author sunfusheng
 * @since 2020/3/26
 */
public class BinaryTreeUtil {
    // 二叉树元素的分隔符
    public static String SEPARATOR = " ";
    public static String NULL_DESC = "#";

    // 递归实现先序遍历
    public static void preOrderRecur(TreeNode head) {
        if (head == null) {
            return;
        }
        System.out.print(head.value + " ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    // 递归实现中序遍历
    public static void inOrderRecur(TreeNode head) {
        if (head == null) {
            return;
        }
        inOrderRecur(head.left);
        System.out.print(head.value + " ");
        inOrderRecur(head.right);
    }

    // 递归实现后序遍历
    public static void posOrderRecur(TreeNode head) {
        if (head == null) {
            return;
        }
        posOrderRecur(head.left);
        posOrderRecur(head.right);
        System.out.print(head.value + " ");
    }

    /**
     * 层遍历反序列化二叉树来创建二叉树
     * <p>
     * 例如下面二叉树：
     * ------- 1
     * ----- /  \
     * ---- 2    3
     * -- / \     \
     * - 4  5      7
     * <p>
     * 先序遍历序二叉树：1 2 4 5 3 7
     * 中序遍历序二叉树：4 2 5 1 3 7
     * 后序遍历序二叉树：4 5 2 7 3 1
     *
     * @param str 按层遍历二叉树的字符串："1 2 3 4 5 # 7 # # # # # #"，提示："#"代表节点为空
     * @return 返回二叉树的头节点
     */
    public static TreeNode deserializeByLevel(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        int index = 0;
        String[] values = str.split(SEPARATOR);
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode head = generateTreeNodeByString(values[index++]);
        if (head != null) {
            queue.offer(head);
        }
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            node.left = generateTreeNodeByString(values[index++]);
            node.right = generateTreeNodeByString(values[index++]);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return head;
    }

    private static TreeNode generateTreeNodeByString(String value) {
        if (value == null || value.equals(NULL_DESC)) {
            return null;
        }
        return new TreeNode(Integer.parseInt(value));
    }

    // 层遍历序列化二叉树
    public static String serializeByLevel(TreeNode head) {
        if (head == null) {
            return NULL_DESC + SEPARATOR;
        }
        StringBuilder res = new StringBuilder(head.value + SEPARATOR);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(head);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null && node.left != null) {
                res.append(node.left.value).append(SEPARATOR);
                queue.offer(node.left);
            } else {
                res.append(NULL_DESC).append(SEPARATOR);
            }

            if (node != null && node.right != null) {
                res.append(node.right.value).append(SEPARATOR);
                queue.offer(node.right);
            } else {
                res.append(NULL_DESC).append(SEPARATOR);
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        // 先构建需要的二叉树
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node7 = new TreeNode(7);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.right = node7;

        System.out.print("先序遍历序二叉树：");
        preOrderRecur(node1);
        System.out.println(" ");
        System.out.print("中序遍历序二叉树：");
        inOrderRecur(node1);
        System.out.println(" ");
        System.out.print("后序遍历序二叉树：");
        posOrderRecur(node1);
        System.out.println("\n");

        System.out.print("层遍历序列化二叉树：");
        String serializeStr = serializeByLevel(node1);
        System.out.println(serializeStr);
        System.out.println(" ");

        System.out.print("层遍历反序列化二叉树后");
        TreeNode root = deserializeByLevel(serializeStr);

        System.out.print("\n先序遍历序二叉树：");
        preOrderRecur(root);
        System.out.println(" ");
        System.out.print("中序遍历序二叉树：");
        inOrderRecur(root);
        System.out.println(" ");
        System.out.print("后序遍历序二叉树：");
        posOrderRecur(root);
    }
}
