package com.wangcheng.leetcode.LeetCode.BinaryTree;

import com.sunfusheng.algo.common.model.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author sunfusheng
 * @since 2020/3/26
 */
public class BinaryTreeUtil {

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
     * 按层遍历，序列化二叉树
     * <p>
     * 例如序列化下面5种结构的二叉树：
     * - 1          3     3      2      1
     * -- \        /     /      / \      \
     * --- 3      2     1      1   3      2
     * -- /      /       \                 \
     * - 2      1         2                 3
     * - 第1个   第2个   第3个   第4个    第5个
     * <p>
     * 分别输出结果如下：
     * 序列化第1个二叉树：1,null,3,2,null
     * 序列化第2个二叉树：3,2,null,1,null
     * 序列化第3个二叉树：3,1,null,null,2
     * 序列化第4个二叉树：2,1,3
     * 序列化第5个二叉树：1,null,2,null,3
     *
     * @param root 要序列化二叉树的根节点
     * @return 返回序列化后的字符串
     */
    public static String serializeByLevel(TreeNode root) {
        if (root == null) {
            return "null";
        }

        StringBuilder res = new StringBuilder(root.value + ",");
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null && node.left != null) {
                if (!res.toString().endsWith(",")) res.append(",");
                res.append(node.left.value);
                queue.offer(node.left);
            } else {
                if (node != null && node.right != null) {
                    if (!res.toString().endsWith(",")) res.append(",");
                    res.append("null");
                }
            }

            if (node != null && node.right != null) {
                if (!res.toString().endsWith(",")) res.append(",");
                res.append(node.right.value);
                queue.offer(node.right);
            } else {
                if (node != null && node.left != null) {
                    if (!res.toString().endsWith(",")) res.append(",");
                    res.append("null");
                }
            }
        }
        return res.toString();
    }

    /**
     * 根据规定的字符串格式反序列化二叉树
     *
     * @param str 需要反序列化的字符串（格式参考{@link #serializeByLevel}的返回值）
     * @return 返回二叉树的根节点
     */
    public static TreeNode deserializeByLevel(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }

        int index = 0;
        String[] values = str.split(",");
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode head = generateTreeNodeByString(values[index++]);
        if (head != null) {
            queue.offer(head);
        }
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null || index >= values.length) {
                break;
            }

            TreeNode left = generateTreeNodeByString(values[index++]);
            TreeNode right = generateTreeNodeByString(values[index++]);
            if (left == null && right == null) {
                continue;
            }

            node.left = left;
            node.right = right;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return head;
    }

    // 生成二叉树的节点
    private static TreeNode generateTreeNodeByString(String value) {
        if (value == null || value.equals("null")) {
            return null;
        }
        return new TreeNode(Integer.parseInt(value));
    }


    public static void main(String[] args) {
        /*
         * 构建如下二叉树
         * ------- 1
         * ----- /  \
         * ---- 2    3
         * -- / \   / \
         * - 4  5  6   7
         */
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;

        System.out.println("打印需要遍历的二叉树：" + node1);
        System.out.print("先序遍历：");
        preOrderRecur(node1);
        System.out.println(" ");
        System.out.print("中序遍历：");
        inOrderRecur(node1);
        System.out.println(" ");
        System.out.print("后序遍历：");
        posOrderRecur(node1);
        System.out.println("\n");

        // 第1个二叉树
        node1 = new TreeNode(1);
        node2 = new TreeNode(2);
        node3 = new TreeNode(3);
        node1.right = node3;
        node3.left = node2;
        System.out.print("序列化第1个二叉树：" + serializeByLevel(node1));
        TreeNode root1 = deserializeByLevel("1,null,3,2,null");
        System.out.println(" ");

        // 第2个二叉树
        node1 = new TreeNode(1);
        node2 = new TreeNode(2);
        node3 = new TreeNode(3);
        node3.left = node2;
        node2.left = node1;
        System.out.print("序列化第2个二叉树：" + serializeByLevel(node3));
        TreeNode root2 = deserializeByLevel("3,2,null,1,null");
        System.out.println(" ");

        // 第3个二叉树
        node1 = new TreeNode(1);
        node2 = new TreeNode(2);
        node3 = new TreeNode(3);
        node3.left = node1;
        node1.right = node2;
        System.out.print("序列化第3个二叉树：" + serializeByLevel(node3));
        TreeNode root3 = deserializeByLevel("3,1,null,null,2");
        System.out.println(" ");

        // 第4个二叉树
        node1 = new TreeNode(1);
        node2 = new TreeNode(2);
        node3 = new TreeNode(3);
        node2.left = node1;
        node2.right = node3;
        System.out.print("序列化第4个二叉树：" + serializeByLevel(node2));
        TreeNode root4 = deserializeByLevel("2,1,3");
        System.out.println(" ");

        // 第5个二叉树
        node1 = new TreeNode(1);
        node2 = new TreeNode(2);
        node3 = new TreeNode(3);
        node1.right = node2;
        node2.right = node3;
        System.out.print("序列化第5个二叉树：" + serializeByLevel(node1));
        TreeNode root5 = deserializeByLevel("1,null,2,null,3");
        System.out.println(" ");

        System.out.println(" ");
        System.out.print("反序列化第1个二叉树：" + root1);
        System.out.println(" ");
        System.out.print("反序列化第2个二叉树：" + root2);
        System.out.println(" ");
        System.out.print("反序列化第3个二叉树：" + root3);
        System.out.println(" ");
        System.out.print("反序列化第4个二叉树：" + root4);
        System.out.println(" ");
        System.out.print("反序列化第5个二叉树：" + root5);
        System.out.println(" ");
    }
}
