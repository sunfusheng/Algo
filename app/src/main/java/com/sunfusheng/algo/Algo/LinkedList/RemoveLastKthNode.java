package com.sunfusheng.algo.Algo.LinkedList;

import com.sunfusheng.algo.Algo.Utils;

/**
 * 【题目】
 * 删除单链表和双链表倒数第K个节点
 * <p>
 * 分别实现两个函数，一个可以删除单链表中倒数第K个节点，另一个可以删除双链表中倒数第K个节点。
 *
 * @author sunfusheng
 * @since 2020/3/7
 */
public class RemoveLastKthNode {

    // 删除单链表中倒数第K个节点
    public static Node removeLastKthNode1(Node head, int lastKth) {
        if (head == null || lastKth < 1) {
            return head;
        }

        Node cur = head;
        while (cur != null) {
            lastKth--;
            cur = cur.next;
        }

        if (lastKth == 0) {
            head = head.next;
        }

        if (lastKth < 0) {
            cur = head;
            while (++lastKth != 0) {
                cur = cur.next;
            }
            cur.next = cur.next.next;
        }
        return head;
    }

    // 删除双链表中倒数第K个节点
    public static DoubleNode removeLastKthNode2(DoubleNode head, int lastKth) {
        if (head == null || lastKth < 1) {
            return head;
        }

        DoubleNode cur = head;
        while (cur != null) {
            lastKth--;
            cur = cur.next;
        }

        if (lastKth == 0) {
            head = head.next;
            head.last = null;
        }

        if (lastKth < 0) {
            cur = head;
            while (++lastKth != 0) {
                cur = cur.next;
            }
            DoubleNode newNode = cur.next.next;
            cur.next = newNode;
            if (newNode != null) {
                newNode.last = cur;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        Node head = Utils.createLinkedList(1, 2, 3, 4, 5);
        Utils.printLinkedList(head);
        head = removeLastKthNode1(head, 2);
        System.out.println("删除单链表中倒数第K个节点后：");
        Utils.printLinkedList(head);

        DoubleNode head2 = Utils.createDoubleLinkedList(1, 2, 3, 4, 5);
        Utils.printDoubleLinkedList(head2);
        head2 = removeLastKthNode2(head2, 3);
        System.out.println("删除双链表中倒数第K个节点后：");
        Utils.printDoubleLinkedList(head2);
    }
}
