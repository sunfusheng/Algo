package com.wangcheng.leetcode.LeetCode.LinkedList;

import com.sunfusheng.algo.common.model.Node;
import com.sunfusheng.algo.common.util.AlgoUtil;

import java.util.Stack;

/**
 * 【题目】
 * 25.K 个一组翻转链表
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * <p>
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * 示例：
 * 给你这个链表：1->2->3->4->5
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 * <p>
 * 说明：
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 * @author sunfusheng
 * @since 2020/5/17
 */
public class ReverseKGroup {

    /**
     * 方法一：栈
     * <p>
     * 时间复杂度：O(N)
     * 空间复杂度：O(K)
     *
     * @param head
     * @param k
     * @return
     */
    public static Node reverseKGroup(Node head, int k) {
        if (head == null || head.next == null || k < 2) {
            return head;
        }

        Stack<Node> stack = new Stack<>();
        Node newHead = head;
        Node cur = head;
        Node pre = null;
        Node next = null;
        while (cur != null) {
            next = cur.next;
            stack.push(cur);
            if (stack.size() == k) {
                pre = reverse1(stack, pre, next);
                newHead = newHead == head ? cur : newHead;
            }
            cur = next;
        }
        return newHead;
    }

    private static Node reverse1(Stack<Node> stack, Node left, Node right) {
        Node cur = stack.pop();
        Node next = null;
        if (left != null) {
            left.next = cur;
        }

        while (!stack.isEmpty()) {
            next = stack.pop();
            cur.next = next;
            cur = next;
        }
        cur.next = right;
        return cur;
    }

    /**
     * 方法二
     * <p>
     * 时间复杂度：O(N)
     * 空间复杂度：O(1)
     *
     * @param head
     * @param k
     * @return
     */
    public static Node reverseKGroup2(Node head, int k) {
        if (head == null || head.next == null || k < 2) {
            return head;
        }

        Node root = new Node(-1);
        root.next = head;
        Node pre = root;
        Node start = root;
        Node end = root;
        Node next = null;
        while (end.next != null) {
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if (end == null) {
                break;
            }
            start = pre.next;
            next = end.next;
            end.next = null;
            pre.next = reverse2(start);
            start.next = next;
            pre = start;
            end = pre;
        }
        return root.next;
    }

    private static Node reverse2(Node head) {
        Node pre = null;
        Node cur = head;
        Node next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        Node list = AlgoUtil.createLinkedList(1, 2, 3, 4, 5);
        System.out.print("方法一输入:");
        AlgoUtil.printLinkedList(list);

        Node res = reverseKGroup(list, 2);
        System.out.print("方法一输出1:");
        AlgoUtil.printLinkedList(res);
        list = AlgoUtil.createLinkedList(1, 2, 3, 4, 5);
        res = reverseKGroup(list, 3);
        System.out.print("方法一输出2:");
        AlgoUtil.printLinkedList(res);

        list = AlgoUtil.createLinkedList(1, 2, 3, 4, 5);
        res = reverseKGroup2(list, 2);
        System.out.print("方法二输出1:");
        AlgoUtil.printLinkedList(res);

        list = AlgoUtil.createLinkedList(1, 2, 3, 4, 5);
        res = reverseKGroup2(list, 3);
        System.out.print("方法二输出2:");
        AlgoUtil.printLinkedList(res);
    }
}
