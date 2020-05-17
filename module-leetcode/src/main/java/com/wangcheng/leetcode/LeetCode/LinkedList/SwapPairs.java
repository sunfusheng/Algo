package com.wangcheng.leetcode.LeetCode.LinkedList;

import com.sunfusheng.algo.common.model.Node;
import com.sunfusheng.algo.common.util.AlgoUtil;

/**
 * 【题目】
 * 24.两两交换链表中的节点
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * 示例:
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 *
 * @author sunfusheng
 * @since 2020/5/17
 */
public class SwapPairs {

    /**
     * 方法一：递归
     * <p>
     * 时间复杂度：O(N)
     * 空间复杂度：O(N)
     *
     * @param head
     * @return
     */
    public static Node swapPairs(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node first = head;
        Node second = head.next;
        first.next = swapPairs(second.next);
        second.next = first;
        return second;
    }

    /**
     * 方法二：迭代
     * <p>
     * 时间复杂度：O(N)
     * 空间复杂度：O(1)
     *
     * @param head
     * @return
     */
    public static Node swapPairs2(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node root = new Node(-1);
        root.next = head;
        Node pre = root;
        while (head != null && head.next != null) {
            Node first = head;
            Node second = head.next;

            pre.next = second;
            first.next = second.next;
            second.next = first;

            pre = first;
            head = head.next;
        }
        return root.next;
    }

    public static void main(String[] args) {
        Node list = AlgoUtil.createLinkedList(1, 2, 3, 4);
        System.out.print("方法一输入：");
        AlgoUtil.printLinkedList(list);
        System.out.print("方法一输出：");
        Node res = swapPairs(list);
        AlgoUtil.printLinkedList(res);

        list = AlgoUtil.createLinkedList(1, 2, 3, 4, 5);
        System.out.print("方法二输入：");
        AlgoUtil.printLinkedList(list);
        System.out.print("方法二输出：");
        res = swapPairs2(list);
        AlgoUtil.printLinkedList(res);
    }
}
