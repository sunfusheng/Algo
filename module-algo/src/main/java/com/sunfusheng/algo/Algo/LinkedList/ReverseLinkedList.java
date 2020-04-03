package com.sunfusheng.algo.Algo.LinkedList;

import com.sunfusheng.algo.common.model.DoubleNode;
import com.sunfusheng.algo.common.model.Node;
import com.sunfusheng.algo.common.util.AlgoUtil;

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
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    // 反转双向链表
    public static DoubleNode reverseDouble(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        System.out.println("反转前的单向链表：");
        Node head = AlgoUtil.createLinkedList(1, 2, 3, 4, 5, 6);
        AlgoUtil.printLinkedList(head);
        System.out.println("反转后的单向链表：");
        head = reverse(head);
        AlgoUtil.printLinkedList(head);

        System.out.println("反转前的双向链表：");
        DoubleNode doubleHead = AlgoUtil.createDoubleLinkedList(11, 22, 33, 44, 55, 66);
        AlgoUtil.printDoubleLinkedList(doubleHead);
        System.out.println("反转后的双向链表：");
        doubleHead = reverseDouble(doubleHead);
        AlgoUtil.printDoubleLinkedList(doubleHead);
    }
}
