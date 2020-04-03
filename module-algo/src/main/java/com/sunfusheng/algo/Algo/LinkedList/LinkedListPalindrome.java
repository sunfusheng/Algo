package com.sunfusheng.algo.Algo.LinkedList;

import com.sunfusheng.algo.common.model.Node;
import com.sunfusheng.algo.common.util.AlgoUtil;

import java.util.Stack;

/**
 * 【题目】
 * 判断一个链表是否为回文结构
 * <p>
 * 给定一个链表的头节点head，请判断该链表是否为回文结构。
 * 例如：
 * 1->2->1，返回true
 * 1->2->2->1，返回true
 * 1->2->3->4，返回false
 * <p>
 * 【进阶问题】
 * 如果链表长度为N，时间复杂度达到O（N），额外空间复杂度达到O（1）。
 *
 * @author sunfusheng
 * @since 2020/4/3
 */
public class LinkedListPalindrome {

    /**
     * 方法一
     * <p>
     * 使用栈结构，链表节点全部压入栈中
     * 空间复杂度O(N)
     *
     * @return
     */
    public static boolean isPalindrome1(Node head) {
        if (head == null || head.next == null) {
            return true;
        }

        Node cur = head;
        Stack<Integer> stack = new Stack<>();
        while (cur != null) {
            stack.push(cur.value);
            cur = cur.next;
        }

        while (head != null) {
            if (head.value != stack.pop()) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    /**
     * 方法二
     * <p>
     * 使用栈结构，将链表右半部分节点压入栈中
     * 空间复杂度O(N)
     *
     * @return
     */
    public static boolean isPalindrome2(Node head) {
        if (head == null || head.next == null) {
            return true;
        }

        Node cur = head;
        Node right = head.next;
        Stack<Integer> stack = new Stack<>();

        while (cur.next != null && cur.next.next != null) {
            cur = cur.next.next;
            right = right.next;
        }

        while (right != null) {
            stack.push(right.value);
            right = right.next;
        }

        while (!stack.isEmpty()) {
            if (head.value != stack.pop()) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    /**
     * 进阶问题解法
     * <p>
     * 先将链表右半部分节点反转，比较完再反转回来
     * 空间复杂度O(1)
     *
     * @return
     */
    public static boolean isPalindrome3(Node head) {
        if (head == null || head.next == null) {
            return true;
        }

        boolean res = true;
        Node n1 = head; // 中部节点
        Node n2 = head; // 右半部分第一个节点
        while (n2.next != null && n2.next.next != null) {
            n1 = n1.next;
            n2 = n2.next.next;
        }
        n2 = n1.next;
        n1.next = null;
        // 反转单向链表右半部分
        n2 = ReverseLinkedList.reverse(n2);
        while (n2 != null) {
            if (n2.value != head.value) {
                res = false;
                break;
            }
            n2 = n2.next;
            head = head.next;
        }

        // 比较完再反转回来
        n1.next = ReverseLinkedList.reverse(n2);
        return res;
    }

    public static void main(String[] args) {
        Node head1 = AlgoUtil.createLinkedList(1, 2, 1);
        Node head2 = AlgoUtil.createLinkedList(1, 2, 2, 1);
        Node head3 = AlgoUtil.createLinkedList(1, 2, 3, 4);

        AlgoUtil.printLinkedList(head1);
        System.out.println("链表是否为回文结构1: " + isPalindrome1(head1));
        AlgoUtil.printLinkedList(head2);
        System.out.println("链表是否为回文结构1: " + isPalindrome1(head2));
        AlgoUtil.printLinkedList(head3);
        System.out.println("链表是否为回文结构1: " + isPalindrome1(head3));

        System.out.println(" ");
        AlgoUtil.printLinkedList(head1);
        System.out.println("链表是否为回文结构2: " + isPalindrome2(head1));
        AlgoUtil.printLinkedList(head2);
        System.out.println("链表是否为回文结构2: " + isPalindrome2(head2));
        AlgoUtil.printLinkedList(head3);
        System.out.println("链表是否为回文结构2: " + isPalindrome2(head3));

        System.out.println(" ");
        AlgoUtil.printLinkedList(head1);
        System.out.println("链表是否为回文结构3: " + isPalindrome3(head1));
        AlgoUtil.printLinkedList(head2);
        System.out.println("链表是否为回文结构3: " + isPalindrome3(head2));
        AlgoUtil.printLinkedList(head3);
        System.out.println("链表是否为回文结构3: " + isPalindrome3(head3));
    }
}
