package com.wangcheng.leetcode.LeetCode.LinkedList;

import com.sunfusheng.algo.common.model.Node;
import com.sunfusheng.algo.common.util.AlgoUtil;

import java.util.Stack;

/**
 * 【题目】
 * 445.两数相加 II
 * 给你两个非空链表来代表两个非负整数。数字最高位位于链表开始位置。
 * 它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * <p>
 * 进阶：
 * 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
 * <p>
 * 示例：
 * 输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 8 -> 0 -> 7
 *
 * @author sunfusheng
 * @since 2020/5/9
 */
public class AddTwoNumbersII {

    /**
     * 方法一：栈
     * <p>
     * 时间复杂度：O(max(m, n)
     * 空间复杂度：O(m + n)
     *
     * @param l1
     * @param l2
     * @return
     */
    public static Node addTwoNumbers(Node l1, Node l2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        while (l1 != null) {
            stack1.push(l1.value);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.value);
            l2 = l2.next;
        }

        Node head = null;
        int carry = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry > 0) {
            int sum = carry;
            sum += stack1.isEmpty() ? 0 : stack1.pop();
            sum += stack2.isEmpty() ? 0 : stack2.pop();
            Node node = new Node(sum % 10);
            node.next = head;
            head = node;
            carry = sum / 10;
        }
        return head;
    }

    public static void main(String[] args) {
        Node l1 = AlgoUtil.createLinkedList(7, 2, 4, 3);
        Node l2 = AlgoUtil.createLinkedList(5, 6, 4);
        System.out.print("输入l1：");
        AlgoUtil.printLinkedList(l1);
        System.out.print("输入l2：");
        AlgoUtil.printLinkedList(l2);
        System.out.print("输出：");
        AlgoUtil.printLinkedList(addTwoNumbers(l1, l2));
    }
}
