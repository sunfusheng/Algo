package com.sunfusheng.algo.Algo.LinkedList;

import com.sunfusheng.algo.common.model.Node;
import com.sunfusheng.algo.common.util.AlgoUtil;

import java.util.Stack;

/**
 * 【题目】
 * 两个单链表生成相加链表
 * <p>
 * 假设链表中每一个节点的值都在0～9之间，那么链表整体就可以代表一个整数。
 * 例如：9-＞3-＞7，可以代表整数937。
 * 给定两个这种链表的头节点head1和head2，请生成代表两个整数相加值的结果链表。
 * 例如：链表1为9-＞3-＞7，链表2为6-＞3，最后生成新的结果链表为1-＞0-＞0-＞0。
 *
 * @author sunfusheng
 * @since 2020/4/13
 */
public class AddTwoLinkedList {

    /**
     * 解法一：利用栈结构求解
     *
     * @param head1 输入链表1
     * @param head2 输入链表2
     * @return 返回相加后的新链表
     */
    public static Node addTwoLinkedList1(Node head1, Node head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }

        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        while (head1 != null) {
            stack1.push(head1.value);
            head1 = head1.next;
        }
        while (head2 != null) {
            stack2.push(head2.value);
            head2 = head2.next;
        }

        int n1 = 0;
        int n2 = 0;
        int n = 0;
        int ca = 0;
        Node pre = null;
        Node node = null;
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            n1 = stack1.isEmpty() ? 0 : stack1.pop();
            n2 = stack2.isEmpty() ? 0 : stack2.pop();
            n = n1 + n2 + ca;
            pre = node;
            node = new Node(n % 10);
            node.next = pre;
            ca = n / 10;
        }

        if (ca == 1) {
            pre = node;
            node = new Node(1);
            node.next = pre;
        }
        return node;
    }

    /**
     * 解法二：
     * 1、先反转两个入参单链表；
     * 2、相加后生成返回结果新链表
     * 3、再反转两个入参单链表，恢复入参结构
     * <p>
     * 反转单向链表 {@link ReverseLinkedList#reverse(Node)}
     *
     * @param head1 输入链表1
     * @param head2 输入链表2
     * @return 返回相加后的新链表
     */
    public static Node addTwoLinkedList2(Node head1, Node head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }

        Node node1 = ReverseLinkedList.reverse(head1);
        Node node2 = ReverseLinkedList.reverse(head2);

        int n1 = 0;
        int n2 = 0;
        int n = 0;
        int ca = 0;
        Node pre = null;
        Node node = null;
        while (node1 != null || node2 != null) {
            n1 = node1 != null ? node1.value : 0;
            n2 = node2 != null ? node2.value : 0;
            n = n1 + n2 + ca;
            pre = node;
            node = new Node(n % 10);
            node.next = pre;
            ca = n / 10;
            node1 = node1 != null ? node1.next : null;
            node2 = node2 != null ? node2.next : null;

        }

        if (ca == 1) {
            pre = node;
            node = new Node(1);
            node.next = pre;
        }

        ReverseLinkedList.reverse(head1);
        ReverseLinkedList.reverse(head2);
        return node;
    }

    public static void main(String[] args) {
        Node head1 = AlgoUtil.createLinkedList(9, 3, 7);
        Node head2 = AlgoUtil.createLinkedList(6, 3);

        System.out.print("输入链表1：");
        AlgoUtil.printLinkedList(head1);
        System.out.print("输入链表2：");
        AlgoUtil.printLinkedList(head2);

        Node res1 = addTwoLinkedList1(head1, head2);
        System.out.print("链表相加后，输出1：");
        AlgoUtil.printLinkedList(res1);

        Node res2 = addTwoLinkedList1(head1, head2);
        System.out.print("链表相加后，输出2：");
        AlgoUtil.printLinkedList(res2);
    }
}
