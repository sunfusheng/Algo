package com.sunfusheng.algo.Algo.LinkedList;

import com.sunfusheng.algo.Algo.Utils;

/**
 * 【题目】
 * 分别实现反转单向链表和双向链表的函数
 *
 * @author sunfusheng
 * @since 2020-02-25
 */
public class ReverseLinkedList {

    // 反转单向链表
    public static Node reverse(Node head) {
        Node pre = null;
        while (head != null) {
            Node next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    // 反转双向链表
    public static DoubleNode reverseDouble(DoubleNode head) {
        DoubleNode pre = null;
        while (head != null) {
            DoubleNode next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        System.out.println("反转前的单向链表：");
        Node head = Utils.createLinkedList(1, 2, 3, 4, 5, 6);
        Utils.printLinkedList(head);
        System.out.println("反转后的单向链表：");
        head = reverse(head);
        Utils.printLinkedList(head);

        System.out.println("反转前的双向链表：");
        DoubleNode doubleHead = Utils.createDoubleLinkedList(11, 22, 33, 44, 55, 66);
        Utils.printDoubleLinkedList(doubleHead);
        System.out.println("反转后的双向链表：");
        doubleHead = reverseDouble(doubleHead);
        Utils.printDoubleLinkedList(doubleHead);
    }
}
