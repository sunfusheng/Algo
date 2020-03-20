package com.sunfusheng.algo.Algo.BinaryTree;

import com.sunfusheng.algo.common.model.TreeNode;

/**
 * 【题目】
 * 打印二叉树的边界节点
 * 给定一棵二叉树的头节点head，按照如下两种标准分别实现二叉树边界节点的逆时针打印。
 * <p>
 * 标准一：
 * 1.头节点为边界节点。
 * 2.叶节点为边界节点。
 * 3.如果节点在其所在的层中是最左的或最右的，那么该节点也是边界节点。
 * <p>
 * 标准二：
 * 1.头节点为边界节点。
 * 2.叶节点为边界节点。
 * 3.树左边界延伸下去的路径为边界节点。
 * 4.树右边界延伸下去的路径为边界节点。
 * <p>
 * 例如下面二叉树：
 * ----------------- 1
 * --------------- /  \
 * -------------- /    \
 * ------------ /       \
 * ---------- /          \
 * -------- /             \
 * ------ 2                3
 * ------ \               / \
 * ------- 4             5   6
 * ------ / \           / \
 * ----- 7   8         9  10
 * ---------- \       /
 * ---------- 11     12
 * --------- / \    / \
 * -------- 13 14  15 16
 * <p>
 * 按标准一的打印结果为：1, 2, 4, 7，11, 13, 14, 15, 16, 12, 10, 6, 3
 * 按标准二的打印结果为：1, 2, 4, 7, 13, 14, 15, 16, 10, 6, 3
 * <p>
 * 【要求】
 * 1.如果节点数为N，两种标准实现的时间复杂度要求都为O（N），额外空间复杂度要求都为O（h），h为二叉树的高度。
 * 2.两种标准都要求逆时针顺序且不重复打印所有的边界节点。
 *
 * @author sunfusheng
 * @since 2020/3/20
 */
public class PrintEdgeNodes {

    // 标准一：打印二叉树的边界节点
    public static void printEdge1(TreeNode head) {
        if (head == null) {
            return;
        }

        int height = getTreeHeight(head, 0);
        TreeNode[][] edgeMap = new TreeNode[height][2];
        setEdgeMap(head, 0, edgeMap);

        // 打印左边界
        for (int i = 0; i < edgeMap.length; i++) {
            System.out.print(edgeMap[i][0].value + " ");
        }

        // 打印既不是左边界也不是右边界的子节点
        printLeafNotInMap(head, 0, edgeMap);

        // 打印右边界，但不是左边界的节点
        for (int i = edgeMap.length - 1; i > 0; i--) {
            System.out.print(edgeMap[i][1].value + " ");
        }
    }

    // 递归获取二叉树的高度
    public static int getTreeHeight(TreeNode head, int height) {
        if (head == null) {
            return height;
        }
        return Math.max(getTreeHeight(head.left, height + 1), getTreeHeight(head.right, height + 1));
    }

    // 设置左右边界节点
    private static void setEdgeMap(TreeNode head, int level, TreeNode[][] edgeMap) {
        if (head == null) {
            return;
        }
        edgeMap[level][0] = edgeMap[level][0] == null ? head : edgeMap[level][0];
        edgeMap[level][1] = head;
        setEdgeMap(head.left, level + 1, edgeMap);
        setEdgeMap(head.right, level + 1, edgeMap);
    }

    // 先序遍历打印既不是左边界也不是右边界的子节点
    private static void printLeafNotInMap(TreeNode head, int level, TreeNode[][] edgeMap) {
        if (head == null) {
            return;
        }

        if (head.left == null && head.right == null && head != edgeMap[level][0] && head != edgeMap[level][1]) {
            System.out.print(head.value + " ");
        }
        printLeafNotInMap(head.left, level + 1, edgeMap);
        printLeafNotInMap(head.right, level + 1, edgeMap);
    }

    // 标准二：打印二叉树的边界节点
    public static void printEdge2(TreeNode head) {
        if (head == null) {
            return;
        }

        System.out.print(head.value + " ");
        if (head.left != null && head.right != null) {
            printLeftEdge(head.left, true);
            printRightEdge(head.right, true);
        } else {
            printEdge2(head.left != null ? head.left : head.right);
        }
    }

    // 先序遍历打印左边界和叶节点
    private static void printLeftEdge(TreeNode head, boolean print) {
        if (head == null) {
            return;
        }

        if (print || (head.left == null && head.right == null)) {
            System.out.print(head.value + " ");
        }
        printLeftEdge(head.left, print);
        printLeftEdge(head.right, print && head.left == null);
    }

    // 后序遍历打印右边界和叶节点
    private static void printRightEdge(TreeNode head, boolean print) {
        if (head == null) {
            return;
        }

        printRightEdge(head.left, print && head.right == null);
        printRightEdge(head.right, print);
        if (print || (head.left == null && head.right == null)) {
            System.out.print(head.value + " ");
        }
    }

    public static void main(String[] args) {
        // 先构建需要的二叉树，体力活啊
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        TreeNode node9 = new TreeNode(9);
        TreeNode node10 = new TreeNode(10);
        TreeNode node11 = new TreeNode(11);
        TreeNode node12 = new TreeNode(12);
        TreeNode node13 = new TreeNode(13);
        TreeNode node14 = new TreeNode(14);
        TreeNode node15 = new TreeNode(15);
        TreeNode node16 = new TreeNode(16);
        node1.left = node2;
        node1.right = node3;
        node2.right = node4;
        node3.left = node5;
        node3.right = node6;
        node4.left = node7;
        node4.right = node8;
        node5.left = node9;
        node5.right = node10;
        node8.right = node11;
        node9.left = node12;
        node11.left = node13;
        node11.right = node14;
        node12.left = node15;
        node12.right = node16;

        System.out.print("递归实现先序遍历：");
        BinaryTreeTraverse.preOrderRecur(node1);
        System.out.print("\n递归实现中序遍历：");
        BinaryTreeTraverse.inOrderRecur(node1);
        System.out.print("\n递归实现后序遍历：");
        BinaryTreeTraverse.posOrderRecur(node1);

        System.out.println("\n");
        System.out.print("递归获取二叉树的高度：" + getTreeHeight(node1, 0) + "层");
        System.out.println(" ");

        System.out.print("标准一：打印二叉树的边界节点：");
        printEdge1(node1);
        System.out.println(" ");

        System.out.print("标准二：打印二叉树的边界节点：");
        printEdge2(node1);
        System.out.println(" ");
    }
}
