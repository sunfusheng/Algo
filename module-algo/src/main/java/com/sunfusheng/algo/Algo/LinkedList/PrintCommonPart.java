package com.sunfusheng.algo.Algo.LinkedList;

import com.sunfusheng.algo.common.model.Node;
import com.sunfusheng.algo.common.util.AlgoUtil;

/**
 * 【题目】
 * 打印两个有序链表的公共部分
 * <p>
 * 给定两个有序链表的头指针 header1 和 header2，打印两个链表的公共部分
 *
 * @author sunfusheng
 * @since 2020/3/2
 */
public class PrintCommonPart {

    public static void printCommonPart(Node header1, Node header2) {
        if (header1 == null || header2 == null) {
            return;
        }

        while (header1 != null && header2 != null) {
            if (header1.value < header2.value) {
                header1 = header1.next;
            } else if (header2.value < header1.value) {
                header2 = header2.next;
            } else {
                System.out.print(header1.value + " -> ");
                header1 = header1.next;
                header2 = header2.next;
            }
        }
    }

    public static void main(String[] args) {
        Node header1 = AlgoUtil.createLinkedList(1, 3, 4, 5, 7, 8);
        Node header2 = AlgoUtil.createLinkedList(2, 3, 5, 8, 9);

        System.out.print("两个有序链表的公共部分:");
        printCommonPart(header1, header2);
    }
}
