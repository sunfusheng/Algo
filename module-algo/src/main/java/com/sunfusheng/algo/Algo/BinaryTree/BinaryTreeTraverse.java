package com.sunfusheng.algo.Algo.BinaryTree;

import com.sunfusheng.algo.common.model.TreeNode;

import java.util.Stack;

/**
 * 【题目】
 * 分别用递归和非递归方式实现二叉树先序、中序、后序遍历
 * <p>
 * 先序遍历顺序：根、左、右
 * 中序遍历顺序：左、根、右
 * 后序遍历顺序：左、右、根
 * <p>
 * 例如下面二叉树：
 * ------- 1
 * ----- /  \
 * ---- 2    3
 * -- / \   / \
 * - 4  5  6   7
 * <p>
 * 先序遍历顺序：1 2 4 5 3 6 7
 * 中序遍历顺序：4 2 5 1 6 3 7
 * 后序遍历顺序：4 5 2 6 7 3 1
 *
 * @author sunfusheng
 * @since 2020/3/14
 */
public class BinaryTreeTraverse {

    // 递归实现先序遍历
    public static void preOrderRecur(TreeNode head) {
        if (head == null) {
            return;
        }
        System.out.print(head.val + " ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    // 递归实现中序遍历
    public static void inOrderRecur(TreeNode head) {
        if (head == null) {
            return;
        }
        inOrderRecur(head.left);
        System.out.print(head.val + " ");
        inOrderRecur(head.right);
    }

    // 递归实现后序遍历
    public static void posOrderRecur(TreeNode head) {
        if (head == null) {
            return;
        }
        posOrderRecur(head.left);
        posOrderRecur(head.right);
        System.out.print(head.val + " ");
    }

    // 非递归实现先序遍历
    public static void preOrderUnRecur(TreeNode head) {
        if (head == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            System.out.print(cur.val + " ");

            if (cur.right != null) {
                stack.push(cur.right);
            }

            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }

    // 非递归实现中序遍历
    public static void inOrderUnRecur(TreeNode head) {
        if (head == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                TreeNode node = stack.pop();
                System.out.print(node.val + " ");
                head = node.right;
            }
        }
    }

    // 非递归实现后序遍历1：使用两个栈
    public static void posOrderUnRecur1(TreeNode head) {
        if (head == null) {
            return;
        }

        // s1出栈的顺序是中、右、左
        Stack<TreeNode> s1 = new Stack<>();
        // 所以，s2的入栈顺序是左、右、中
        Stack<TreeNode> s2 = new Stack<>();
        s1.push(head);
        while (!s1.isEmpty()) {
            head = s1.pop();
            s2.push(head);

            if (head.left != null) {
                s1.push(head.left);
            }

            if (head.right != null) {
                s1.push(head.right);
            }
        }

        while (!s2.isEmpty()) {
            System.out.print(s2.pop().val + " ");
        }
    }

    // 非递归实现后序遍历2：使用一个栈
    public static void posOrderUnRecur2(TreeNode head) {
        if (head == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(head);
        // cur为栈顶节点，head为最近一次弹出并打印的节点
        TreeNode cur = null;
        while (!stack.isEmpty()) {
            cur = stack.peek();
            if (cur.left != null && head != cur.left && head != cur.right) {
                stack.push(cur.left);
            } else if (cur.right != null && head != cur.right) {
                stack.push(cur.right);
            } else {
                System.out.print(stack.pop().val + " ");
                head = cur;
            }
        }
    }

    public static void main(String[] args) {
        // 先构建需要的二叉树
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

        System.out.print("递归实现先序遍历：");
        preOrderRecur(node1);
        System.out.print("\n递归实现中序遍历：");
        inOrderRecur(node1);
        System.out.print("\n递归实现后序遍历：");
        posOrderRecur(node1);

        System.out.print("\n非递归实现先序遍历：");
        preOrderUnRecur(node1);
        System.out.print("\n非递归实现中序遍历：");
        inOrderUnRecur(node1);
        System.out.print("\n非递归实现后序遍历1：");
        posOrderUnRecur1(node1);
        System.out.print("\n非递归实现后序遍历2：");
        posOrderUnRecur2(node1);
    }
}
