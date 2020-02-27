package com.sunfusheng.algo.Algo.LinkedList;

import com.sunfusheng.algo.Algo.Utils;

/**
 * 【题目】
 * 反转部分单向链表
 * <p>
 * 给定一个单向链表的头节点head，以及两个整数from和to，
 * 在单向链表上把第from个节点到第to个节点这一部分进行反转。
 * <p>
 * 例如：1-＞2-＞3-＞4-＞5->6, from=2, to=5
 * 调整结果为：1-＞5->4-＞3-＞2-＞6
 * <p>
 * 再如：1-＞2-＞3, from=1, to=3
 * 调整结果为：3-＞2-＞1
 * <p>
 * 【要求】
 * 1.如果链表长度为N，时间复杂度要求为O（N），额外空间复杂度要求为O（1）。
 * 2.如果不满足1＜=from＜=to＜=N，则不用调整。
 *
 * @author sunfusheng
 * @since 2020-02-25
 */
public class ReversePartLinkedList {

    // 反转部分单向链表
    public static Node reversePart(Node head, int from, int to) {
        int len = 0;
        Node node1 = head;
        Node fPos = null;
        Node tPos = null;
        while (node1 != null) {
            len++;
            fPos = (len == from - 1) ? node1 : fPos;
            tPos = (len == to + 1) ? node1 : tPos;
            node1 = node1.next;
        }

        if (from > to || from < 1 || to > len) {
            return head;
        }

        node1 = (fPos == null) ? head : fPos.next;
        Node node2 = node1.next;
        node1.next = tPos;
        Node next = null;
        while (node2 != tPos) {
            next = node2.next;
            node2.next = node1;
            node1 = node2;
            node2 = next;
        }

        if (fPos != null) {
            fPos.next = node1;
            return head;
        }
        return node1;
    }

    public static void main(String[] args) {
        Node head = Utils.createLinkedList(1, 2, 3, 4, 5, 6);
        head = reversePart(head, 2, 5);
        Utils.printLinkedList(head);
    }
}
