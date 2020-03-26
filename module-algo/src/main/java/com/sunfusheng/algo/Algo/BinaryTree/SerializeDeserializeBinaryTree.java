package com.sunfusheng.algo.Algo.BinaryTree;

import com.sunfusheng.algo.common.model.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 【题目】
 * 二叉树的序列化和反序列化
 * <p>
 * 二叉树被记录成文件的过程叫作二叉树的序列化，
 * 通过文件内容重建原来二叉树的过程叫作二叉树的反序列化。
 * 给定一棵二叉树的头节点head，已知二叉树节点值的类型为32位整型。
 * 请设计一种二叉树序列化和反序列化的方案，并用代码实现。
 * <p>
 * 例如下面二叉树：
 * ------- 1
 * ----- /  \
 * ---- 2    3
 * -- / \     \
 * - 4  5      7
 * <p>
 * 先序遍历顺序：1 2 4 5 3 7
 * 先序遍历序列化二叉树：1 2 4 # # 5 # # 3 # 7 # #
 * 层遍历序列化二叉树：1 2 3 4 5 # 7 # # # # # #
 *
 * @author sunfusheng
 * @since 2020/3/21
 */
public class SerializeDeserializeBinaryTree {
    // 二叉树元素的分隔符
    public static String SEPARATOR = " ";

    // 先序遍历序列化二叉树
    public static String serializeByPreOrder(TreeNode head) {
        if (head == null) {
            return "#" + SEPARATOR;
        }
        String res = head.value + SEPARATOR;
        res += serializeByPreOrder(head.left);
        res += serializeByPreOrder(head.right);
        return res;
    }

    // 先序遍历反序列化二叉树
    public static TreeNode deserializeByPreOrder(String str) {
        String[] values = str.split(SEPARATOR);
        if (values.length == 0) {
            return null;
        }

        Queue<String> queue = new LinkedList<>();
        for (String value : values) {
            queue.offer(value);
        }
        return deserializeByPreOrderInternal(queue);
    }

    private static TreeNode deserializeByPreOrderInternal(Queue<String> queue) {
        String value = queue.poll();
        if (value == null || "#".equals(value)) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(value));
        node.left = deserializeByPreOrderInternal(queue);
        node.right = deserializeByPreOrderInternal(queue);
        return node;
    }

    // 层遍历序列化二叉树
    public static String serializeByLevel(TreeNode head) {
        if (head == null) {
            return "#" + SEPARATOR;
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
                res.append("#").append(SEPARATOR);
            }

            if (node != null && node.right != null) {
                res.append(node.right.value).append(SEPARATOR);
                queue.offer(node.right);
            } else {
                res.append("#").append(SEPARATOR);
            }
        }
        return res.toString();
    }

    // 层遍历反序列化二叉树
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
        if (value == null || value.equals("#")) {
            return null;
        }
        return new TreeNode(Integer.parseInt(value));
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

        System.out.println("先序遍历序列化二叉树：");
        String serialTree = serializeByPreOrder(node1);
        System.out.println(serialTree);

        System.out.println("先序遍历反序列化二叉树后，先序遍历二叉树：");
        TreeNode head = deserializeByPreOrder(serialTree);
        BinaryTreeTraverse.preOrderRecur(head);

        System.out.println("\n层遍历序列化二叉树：");
        serialTree = serializeByLevel(node1);
        System.out.println(serialTree);

        System.out.println("层遍历反序列化二叉树，先序遍历二叉树：");
        head = deserializeByLevel(serialTree);
        BinaryTreeTraverse.preOrderRecur(head);
    }
}
